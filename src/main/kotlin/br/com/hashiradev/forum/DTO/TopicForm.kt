package br.com.hashiradev.forum.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicForm(
    @field:NotBlank @field:Size(min = 5) val title: String,
    @field:NotBlank val message: String,
    @field:NotNull val userID: Long,
    @field:NotNull val courseID: Long,
)
