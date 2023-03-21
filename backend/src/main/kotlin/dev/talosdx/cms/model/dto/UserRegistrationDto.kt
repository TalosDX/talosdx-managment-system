package dev.talosdx.cms.model.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class UserRegistrationDto(
    @field:NotBlank
    @field:Min(4)
    @field:Max(20)
    val username: String,
    @field:NotBlank
    @field:Min(4)
    @field:Max(63)
    var password: String,
    @field:Email
    @field:NotBlank
    val email: String,
)

