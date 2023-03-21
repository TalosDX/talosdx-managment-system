package dev.talosdx.cms.model.service.prototype

import org.springframework.security.core.userdetails.UserDetailsService


private const val LOGOUT_SUCCESS_URL = "/"

interface AuthService : UserDetailsService {

    fun isAuthenticated(): Boolean

    fun logout(): String
}