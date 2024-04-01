package br.com.hashiradev.forum.controller

import br.com.hashiradev.forum.DTO.TopicForm
import br.com.hashiradev.forum.DTO.TopicUpdateForm
import br.com.hashiradev.forum.DTO.TopicView
import br.com.hashiradev.forum.service.TopicService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topic")
class TopicController(private val topicService: TopicService) {
    @GetMapping
    @Cacheable("topics-cache")
    fun index(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pagination: Pageable,
    ) = topicService.index(courseName, pagination)

    @GetMapping("/{id}")
    fun findByID(@PathVariable id: Long) = topicService.findByID(id)

    @PostMapping
    @Transactional
    @CacheEvict(*["topics-cache"], allEntries = true)
    fun create(
        @RequestBody @Valid form: TopicForm,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<TopicView> {
        val topicView = topicService.create(form)
        val uri = uriBuilder.path("/topic/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topics-cache"], allEntries = true)
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid form: TopicUpdateForm,
    ): ResponseEntity<TopicView> = ResponseEntity.ok(topicService.update(id, form))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topics-cache"], allEntries = true)
    fun delete(@PathVariable id: Long) = topicService.delete(id)
}