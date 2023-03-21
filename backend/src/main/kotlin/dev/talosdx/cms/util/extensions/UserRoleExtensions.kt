package dev.talosdx.cms.util.extensions

import dev.talosdx.cms.model.dto.UserRoleDto
import dev.talosdx.cms.model.entity.UserRole

fun UserRole.toDto() = UserRoleDto(
    name,
    description,
    id
)

fun UserRoleDto.toEntity(): UserRole = UserRole(
    name,
    description
)