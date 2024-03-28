package br.com.hashiradev.forum.repository

import br.com.hashiradev.forum.model.AnswerModel
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<AnswerModel, Long> {
}