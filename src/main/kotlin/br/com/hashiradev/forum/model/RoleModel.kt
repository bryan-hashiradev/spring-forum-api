package br.com.hashiradev.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority

@Entity(name = "roles")
data class RoleModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long,
     val name: String,
): GrantedAuthority {
    override fun getAuthority() = name
}
