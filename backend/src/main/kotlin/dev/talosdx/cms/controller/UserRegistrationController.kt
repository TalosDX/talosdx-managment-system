package dev.talosdx.cms.controller

import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.service.prototype.UserRegistrationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1/user-registration"])
class UserRegistrationController(
    val service: UserRegistrationService,
) {

    @PostMapping
    fun registerUser(@RequestBody userData: UserRegistrationDto) {
        service.registerUser(userData)
    }
}