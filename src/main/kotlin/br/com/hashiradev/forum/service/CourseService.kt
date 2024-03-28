package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.exception.NotFoundException
import br.com.hashiradev.forum.model.CourseModel
import br.com.hashiradev.forum.repository.CourseRepository
import br.com.hashiradev.forum.utils.TextMessages
import org.springframework.stereotype.Service

@Service
class CourseService(
    private val repository: CourseRepository
) {
    fun index() = repository.findAll()
    fun getByID(id: Long): CourseModel = repository.findById(id).orElseThrow { NotFoundException(TextMessages.COURSE_NOT_FOUND_ERROR) }
}