package br.com.hashiradev.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity(name = "users")
data class UserModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    @JsonIgnore
    val roles: List<RoleModel> = mutableListOf(),
)
