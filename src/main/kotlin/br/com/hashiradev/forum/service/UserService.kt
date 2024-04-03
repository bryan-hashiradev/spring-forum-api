package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.exception.NotFoundException
import br.com.hashiradev.forum.model.UserModel
import br.com.hashiradev.forum.repository.UserRepository
import br.com.hashiradev.forum.utils.TextMessages
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
): UserDetailsService {

    fun index() = repository.findAll()
    fun findByID(id: Long): UserModel = repository.findById(id).orElseThrow { NotFoundException(TextMessages.USER_NOT_FOUND_ERROR) }
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetailsImpl(user)
    }
}