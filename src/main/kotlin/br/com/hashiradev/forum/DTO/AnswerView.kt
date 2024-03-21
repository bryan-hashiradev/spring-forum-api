package br.com.hashiradev.forum.DTO

data class AnswerView(
    val id: Long,
    val message: String,
    val authorName: String,
    val solution: Boolean
)
