package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.DTO.AnswerForm
import br.com.hashiradev.forum.DTO.AnswerUpdateForm
import br.com.hashiradev.forum.DTO.AnswerView
import br.com.hashiradev.forum.exception.NotFoundException
import br.com.hashiradev.forum.mapper.AnswerModelMapper
import br.com.hashiradev.forum.mapper.AnswerViewMapper
import br.com.hashiradev.forum.model.AnswerModel
import br.com.hashiradev.forum.repository.AnswerRepository
import br.com.hashiradev.forum.utils.TextMessages
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val repository: AnswerRepository,
    private val answerModelMapper: AnswerModelMapper,
    private val answerViewMapper: AnswerViewMapper,
) {
    fun create(answer: AnswerForm) {
        val answerModel = answerModelMapper.map(answer)
        repository.save(answerModel)
    }

    fun update(id: Long, answerUpdateForm: AnswerUpdateForm) {
        val answer = repository.findById(id)
            .orElseThrow { throw NotFoundException(TextMessages.ANSWER_NOT_FOUND_ERROR) }
            .apply {
                this.message = answerUpdateForm.message
            }
        repository.save(answer)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun findAnswersByTopicID(id: Long): List<AnswerView> {
        return repository.findByTopicId(id).map { answerViewMapper.map(it) }
    }
}