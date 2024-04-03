package br.com.hashiradev.forum.repository

import br.com.hashiradev.forum.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserModel, Long> {
     fun findByEmail(username: String?): UserModel?
}