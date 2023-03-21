package dev.talosdx.cms.util.extensions

import dev.talosdx.cms.model.dto.UserDto
import dev.talosdx.cms.model.entity.User

fun User.toDto() = UserDto(
    username,
    password,
    email,
    userRole.toDto(),
    activationTime,
    isActivated,
    activationCode,
    id,
    createdDate,
    modifiedDate
)

fun UserDto.toEntity(): User = User(
    username,
    password,
    email,
    userRole.toEntity()
)