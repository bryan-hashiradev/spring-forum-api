package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.DTO.AnswerForm
import br.com.hashiradev.forum.mapper.AnswerModelMapper
import br.com.hashiradev.forum.model.AnswerModel
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private var answers: MutableList<AnswerModel> = mutableListOf<AnswerModel>(),
    private val answerModelMapper: AnswerModelMapper,
) {
    fun index(): List<AnswerModel> {
        return answers
    }

    fun create(answer: AnswerForm) {
        val answerModel = answerModelMapper.map(answer)
        answerModel.id = (answers.size + 1).toLong()
        answers.add(answerModel)
    }

    fun findByID(id: Long) {
        answers.first { it.id == id }
    }

}