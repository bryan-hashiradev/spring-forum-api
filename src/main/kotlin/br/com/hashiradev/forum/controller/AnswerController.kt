package br.com.hashiradev.forum.controller

import br.com.hashiradev.forum.DTO.AnswerForm
import br.com.hashiradev.forum.DTO.AnswerUpdateForm
import br.com.hashiradev.forum.service.AnswerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/answer")
class AnswerController(
    private val answerService: AnswerService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid answerForm: AnswerForm) = answerService.create(answerForm)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Long, @RequestBody @Valid answerUpdateForm: AnswerUpdateForm) =
        answerService.update(id, answerUpdateForm)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = answerService.delete(id)


    @GetMapping("/topic/{id}")
    fun findAnswersByTopicID(@PathVariable id: Long) = answerService.findAnswersByTopicID(id)
}
