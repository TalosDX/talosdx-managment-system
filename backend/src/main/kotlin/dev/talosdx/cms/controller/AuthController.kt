package dev.talosdx.cms.controller

import dev.talosdx.cms.model.service.AuthServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1/auth"])
class AuthController(
    val authService: AuthServiceImpl,
) {
    @GetMapping(path = ["/isAuthenticated"])
    fun isAuthenticated() = authService.isAuthenticated()

    @PutMapping(path = ["/logout"])
    fun logout() = authService.logout()
}