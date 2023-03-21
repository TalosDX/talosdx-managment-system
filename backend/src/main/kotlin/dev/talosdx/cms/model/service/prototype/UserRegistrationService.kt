package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.entity.User

interface UserRegistrationService {

    fun registerUser(userData: UserRegistrationDto, internalRegistration: Boolean = false) : Boolean

    fun generateActivationCode(user: User)

    fun sendActivationCodeToEmail(user: User) : Boolean
}