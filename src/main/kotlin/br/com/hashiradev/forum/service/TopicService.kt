package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.DTO.TopicForm
import br.com.hashiradev.forum.DTO.TopicView
import br.com.hashiradev.forum.mapper.TopicModelMapper
import br.com.hashiradev.forum.mapper.TopicViewMapper
import br.com.hashiradev.forum.model.TopicModel
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<TopicModel> = mutableListOf(),
    private val topicViewMapper: TopicViewMapper,
    private val topicModelMapper: TopicModelMapper,
) {

    fun index(): List<TopicView> {
        return topics.map { topicViewMapper.map(it) }
    }

    fun findByID(id: Long): TopicView? = topics.find { it.id?.equals(id) ?: false }?.let { topicViewMapper.map(it) }
    fun create(topicForm: TopicForm) {
        val topic = topicModelMapper.map(topicForm)

        topic.apply { this.id = (topics.size+1).toLong() }

        topics.add(topic)

    }

}