package br.com.hashiradev.forum.controller

import br.com.hashiradev.forum.DTO.AnswerForm
import br.com.hashiradev.forum.service.AnswerService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/answer")
class AnswerController(
    private val answerService: AnswerService,
) {
    @PostMapping
    fun create(@RequestBody @Valid answerForm: AnswerForm) = answerService.create(answerForm)
}
