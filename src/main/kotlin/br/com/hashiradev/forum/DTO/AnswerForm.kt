package br.com.hashiradev.forum.DTO

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class AnswerForm(
    @field:NotNull @field:Positive
    val authorID: Long,
    @field:NotNull @field:Positive
    val topicID: Long,
    @field:NotNull @field:NotEmpty
    val message: String,
)
