package br.com.hashiradev.forum.controller

import br.com.hashiradev.forum.DTO.TopicForm
import br.com.hashiradev.forum.service.TopicService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topic")
class TopicController(private val service: TopicService) {
    @GetMapping
    fun index() = service.index()
    @GetMapping("/{id}")
    fun findByID(@PathVariable id: Long) = service.findByID(id)
    @PostMapping
    fun create(@RequestBody @Valid topicDTO: TopicForm) = service.create(topicDTO)

}