package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.DTO.AnswerForm
import br.com.hashiradev.forum.DTO.AnswerUpdateForm
import br.com.hashiradev.forum.DTO.AnswerView
import br.com.hashiradev.forum.mapper.AnswerModelMapper
import br.com.hashiradev.forum.mapper.AnswerViewMapper
import br.com.hashiradev.forum.model.AnswerModel
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private var answers: MutableList<AnswerModel> = mutableListOf<AnswerModel>(),
    private val answerModelMapper: AnswerModelMapper,
    private val answerViewMapper: AnswerViewMapper,
) {
    fun create(answer: AnswerForm) {
        val answerModel = answerModelMapper.map(answer)
        answerModel.id = (answers.size + 1).toLong()
        answerModel.topic.answers.add(answerModel)
        answers.add(answerModel)
    }

    fun update(id: Long, answerUpdateForm: AnswerUpdateForm) {
        val answer = answers.first { it.id == id }
        answer.topic.answers.remove(answer)
        answers.remove(answer)

        answer.message = answerUpdateForm.message

        answer.topic.answers.add(answer)
        answers.add(answer)

    }

    fun delete(id: Long) {
        val answer = answers.first { it.id == id }
        answer.topic.answers.remove(answer)
        answers.remove(answer)
    }

    fun findAnswersByTopicID(id: Long): List<AnswerView> {
        return answers.filter { it.topic.id == id }
            .map { answerViewMapper.map(it) }
    }
}