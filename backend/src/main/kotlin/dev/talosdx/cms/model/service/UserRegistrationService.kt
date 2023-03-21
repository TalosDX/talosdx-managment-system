package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.dto.MailDto
import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.entity.User
import dev.talosdx.cms.model.service.prototype.MailService
import dev.talosdx.cms.model.service.prototype.UserRegistrationService
import dev.talosdx.cms.model.service.prototype.UserService
import dev.talosdx.cms.util.exception.AlreadyExistsException
import mu.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserRegistrationServiceImpl(
    private val mailService: MailService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
) : UserRegistrationService {
    private val log = KotlinLogging.logger {}

    override fun registerUser(userData: UserRegistrationDto, internalRegistration: Boolean): Boolean {
        if (userService.existsByUsernameIgnoreCase(userData.username)) {
            throw AlreadyExistsException("User with name ${userData.username} already registered")
        }

        userData.password = passwordEncoder.encode(userData.password)
        val user = userService.createUser(userData)

        if (internalRegistration) {
            user.isActivated = true
            user.activationCode = null
            return true
        } else {
            generateActivationCode(user)
            return sendActivationCodeToEmail(user)
        }
        //todo for future
    }

    override fun generateActivationCode(user: User) {
        user.activationCode = UUID.randomUUID().toString()
    }

    override fun sendActivationCodeToEmail(user: User): Boolean {
        return mailService.sendMail(
            MailDto(
                mutableListOf(user.email),
                "noreply@talosdx.dev",
                "Registration on talosdx.dev",
                "Here your activation code: ${user.activationCode} \n" +
                        "Or y may click this link: <NO IMPLEMENTED>",
                mutableListOf(user)
            )
        )
    }
}