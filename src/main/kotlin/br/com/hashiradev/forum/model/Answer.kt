package br.com.hashiradev.forum.model

import java.time.LocalDateTime

data class Answer(
    val id: Long? = null,
    val message: String,
    val author: User,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val topic: Topic,
    val isSolution: Boolean,
)
