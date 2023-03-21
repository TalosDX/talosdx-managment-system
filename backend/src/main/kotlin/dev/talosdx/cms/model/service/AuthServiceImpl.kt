package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.service.prototype.AuthService
import dev.talosdx.cms.model.service.prototype.UserService
import mu.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


private const val LOGOUT_SUCCESS_URL = "/"

@Service
@Transactional
class AuthServiceImpl(
    private val userService: UserService,
) : AuthService {
    private val log = KotlinLogging.logger {}


    override fun loadUserByUsername(username: String?) = userService
        .loadUserByUsername(username)
        .toUserDetails()

    override fun isAuthenticated(): Boolean {
        val context = SecurityContextHolder.getContext()
        val principal = context?.authentication?.principal ?: return false

        return principal is UserDetails
    }

    override fun logout(): String {
        return "stub"
    }
}