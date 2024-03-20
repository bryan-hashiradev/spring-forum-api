package br.com.hashiradev.forum.model

import java.time.LocalDateTime

data class AnswerModel(
    var id: Long? = null,
    var message: String,
    val author: UserModel,
    val topic: TopicModel,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isSolution: Boolean = false,
)
