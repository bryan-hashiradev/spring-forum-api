package br.com.hashiradev.forum.repository

import br.com.hashiradev.forum.model.CourseModel
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<CourseModel, Long> {
}