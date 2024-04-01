package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.DTO.TopicForm
import br.com.hashiradev.forum.DTO.TopicUpdateForm
import br.com.hashiradev.forum.DTO.TopicView
import br.com.hashiradev.forum.exception.NotFoundException
import br.com.hashiradev.forum.mapper.TopicModelMapper
import br.com.hashiradev.forum.mapper.TopicViewMapper
import br.com.hashiradev.forum.model.TopicModel
import br.com.hashiradev.forum.repository.TopicRepository
import br.com.hashiradev.forum.utils.TextMessages
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicModelMapper: TopicModelMapper,
) {

    fun findModelByID(id: Long): TopicModel = repository.findById(id)
            .orElseThrow { NotFoundException(TextMessages.TOPIC_NOT_FOUND_ERROR) }
    fun index(courseName: String?, pagination: Pageable): Page<TopicView> {
        return when(courseName) {
            null -> repository.findAll(pagination)
            else -> repository.findByCourseName(courseName, pagination)
        }.let { it.map { topicViewMapper.map(it) } }
    }

    fun findByID(id: Long): TopicView = findModelByID(id)
            .let { topicViewMapper.map(it) }

    fun create(topicForm: TopicForm): TopicView {
        val topic = topicModelMapper.map(topicForm)
        return topicViewMapper.map(repository.save(topic))
    }
    fun update(id: Long, topicUpdateForm: TopicUpdateForm): TopicView {

        val topic = repository.findById(id).get()
        topic.apply {
            this.title = topicUpdateForm.title
            this.message = topicUpdateForm.message
        }

        return topicViewMapper.map(repository.save(topic))
    }

    fun delete(id: Long) {
        repository.delete(repository.findById(id).get())
    }
}