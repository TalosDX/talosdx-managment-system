package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.entity.User
import dev.talosdx.cms.model.entity.UserRole
import java.time.ZonedDateTime

interface UserService {
    fun existsUserByTelegramUserId(userId: Long): Boolean

    fun existsByUsernameIgnoreCase(username: String): Boolean

    fun createUser(user: UserRegistrationDto): User

    fun updateUser(user: User) : Boolean

    fun blockUser(
        blockingReasonIn: String,
        userId: Long? = null,
        userIn: User? = null,
        blockingUntilDateIn: ZonedDateTime? = null,
    ): Boolean

    fun getDefaultUserGroup(): UserRole

    fun loadUserByUsername(username: String?): User

    fun createGroup(userRole: UserRole): UserRole
}