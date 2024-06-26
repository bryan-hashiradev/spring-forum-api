package br.com.hashiradev.forum.adapter

import br.com.hashiradev.forum.model.UserModel
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImplAdapter(private val userModel: UserModel): UserDetails {
    override fun getAuthorities() = userModel.roles

    override fun getPassword() = userModel.password

    override fun getUsername() = userModel.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}