package br.com.hashiradev.forum.mapper

import br.com.hashiradev.forum.DTO.AnswerForm
import br.com.hashiradev.forum.model.AnswerModel
import br.com.hashiradev.forum.service.TopicService
import br.com.hashiradev.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class AnswerModelMapper(
    private val userService: UserService,
    private val topicService: TopicService
): Mapper<AnswerForm, AnswerModel> {
    override fun map(answerForm: AnswerForm): AnswerModel = AnswerModel(
        null,
        answerForm.message,
        userService.findByID(answerForm.authorID),
        topicService.findModelByID(answerForm.topicID),
    )
}