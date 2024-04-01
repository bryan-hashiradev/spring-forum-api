package br.com.hashiradev.forum.DTO

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class TopicUpdateForm(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty @field:Size(min = 5, message = "message size error!")
    val message: String
)
