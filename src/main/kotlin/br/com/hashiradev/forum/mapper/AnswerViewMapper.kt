package br.com.hashiradev.forum.mapper

import br.com.hashiradev.forum.DTO.AnswerView
import br.com.hashiradev.forum.model.AnswerModel
import org.springframework.stereotype.Component

@Component
class AnswerViewMapper:Mapper<AnswerModel, AnswerView> {
    override fun map(answerModel: AnswerModel): AnswerView = AnswerView(
        answerModel.id!!,
        answerModel.message,
        answerModel.author.name,
        answerModel.isSolution,
    )
}