package br.com.hashiradev.forum.DTO

import br.com.hashiradev.forum.model.ENUM.TopicStatus
import java.time.LocalDateTime

data class TopicView(
    val id: Long? = null,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val createdAt: LocalDateTime
)
