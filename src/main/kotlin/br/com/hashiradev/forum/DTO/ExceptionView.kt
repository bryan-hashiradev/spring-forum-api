package br.com.hashiradev.forum.DTO

import java.time.LocalDateTime

data class ExceptionView(
    val status: Int,
    val error: String,
    val message: String?,
    val path: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)
