package dev.talosdx.cms.util.extensions

import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.entity.User
import dev.talosdx.cms.model.entity.UserRole

fun UserRegistrationDto.toEntity(userRole: UserRole) = User(
    email,
    username,
    password,
    userRole,
)