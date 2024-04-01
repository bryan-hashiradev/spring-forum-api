package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.exception.NotFoundException
import br.com.hashiradev.forum.model.UserModel
import br.com.hashiradev.forum.repository.UserRepository
import br.com.hashiradev.forum.utils.TextMessages
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    fun index() = repository.findAll()
    fun findByID(id: Long): UserModel = repository.findById(id).orElseThrow { NotFoundException(TextMessages.USER_NOT_FOUND_ERROR) }
}