package br.com.hashiradev.forum.model

import java.time.LocalDateTime

data class AnswerModel(
    val id: Long? = null,
    val message: String,
    val author: UserModel,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val topic: TopicModel,
    val isSolution: Boolean,
)
