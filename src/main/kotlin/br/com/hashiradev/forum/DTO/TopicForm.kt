package br.com.hashiradev.forum.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicForm(
    @field:NotBlank
    val title: String,
    @field:NotBlank(message = "o campo message não pode ser vazio") //testando erro custom
    @field:Size(min = 5, max = 100)
    val message: String,
    @field:NotNull
    val userID: Long,
    @field:NotNull
    val courseID: Long,
)
