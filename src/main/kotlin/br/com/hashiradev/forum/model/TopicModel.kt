package br.com.hashiradev.forum.model

import br.com.hashiradev.forum.model.ENUM.TopicStatus
import java.time.LocalDateTime

data class TopicModel(
    var id: Long? = null,
    var title: String,
    var message: String,
    val user: UserModel,
    val course: CourseModel,
    val createAt: LocalDateTime = LocalDateTime.now(),
    val status: TopicStatus = TopicStatus.OPEN,
    val answers: ArrayList<AnswerModel> = ArrayList()
)
