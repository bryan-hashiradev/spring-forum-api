package br.com.hashiradev.forum.repository

import br.com.hashiradev.forum.model.TopicModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<TopicModel, Long> {
     fun findByCourseName(courseName: String, pagination: Pageable): Page<TopicModel>
}