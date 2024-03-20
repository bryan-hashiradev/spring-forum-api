package br.com.hashiradev.forum.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AnswerUpdateForm(
    @NotBlank @Size(min = 5, max = 100)
    val message: String
)
