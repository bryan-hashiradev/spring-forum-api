package br.com.hashiradev.forum.model

import br.com.hashiradev.forum.model.ENUM.TopicStatus
import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,
    val createAt: LocalDateTime,
    val course: Course,
    val user: User,
    val status: TopicStatus = TopicStatus.OPEN,
    val answers: ArrayList<Answer> = ArrayList()
)
