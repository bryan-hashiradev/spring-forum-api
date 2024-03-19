package br.com.hashiradev.forum.service

import br.com.hashiradev.forum.model.UserModel
import org.springframework.stereotype.Service

@Service
class UserService(
    private var users: List<UserModel>
) {
    init {
        users = listOf(
            UserModel(
                1,
                "Bryan G",
                "mail.bryan@email.com"
            ),
            UserModel(
                2,
                "Renan G",
                "mail.raposas@email.com"
            ),
            UserModel(
                3,
                "Debora C",
                "mail.denna@email.com"
            ),
        )
    }

    fun index() = users
    fun getByID(id: Long): UserModel = users.first { it.id == id }
}