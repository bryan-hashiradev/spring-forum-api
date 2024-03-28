package br.com.hashiradev.forum.repository

import br.com.hashiradev.forum.model.TopicModel
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<TopicModel, Long> {
}