package br.com.hashiradev.forum.controller

import br.com.hashiradev.forum.DTO.TopicForm
import br.com.hashiradev.forum.DTO.TopicUpdateForm
import br.com.hashiradev.forum.service.TopicService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topic")
class TopicController(private val topicService: TopicService) {
    @GetMapping
    fun index() = topicService.index()
    @GetMapping("/{id}")
    fun findByID(@PathVariable id: Long) = topicService.findByID(id)
    @PostMapping
    fun create(@RequestBody @Valid form: TopicForm) = topicService.create(form)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid form: TopicUpdateForm) = topicService.update(id, form)

}