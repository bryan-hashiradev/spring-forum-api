package br.com.hashiradev.forum.mapper

import br.com.hashiradev.forum.DTO.TopicForm
import br.com.hashiradev.forum.model.TopicModel
import br.com.hashiradev.forum.service.CourseService
import br.com.hashiradev.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicModelMapper(
    private val userService: UserService,
    private val courseService: CourseService,
) : Mapper<TopicForm, TopicModel> {
    override fun map(topicForm: TopicForm): TopicModel = TopicModel(
        null,
        topicForm.title,
        topicForm.message,
        userService.findByID(topicForm.userID),
        courseService.getByID(topicForm.courseID),
    )
}