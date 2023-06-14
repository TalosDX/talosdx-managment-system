package dev.talosdx.cms.model.dto

import dev.talosdx.cms.model.dto.prototype.BaseLongAuditDto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.time.ZonedDateTime

data class UserDto(
    @field:NotBlank
    @field:Min(4)
    @field:Max(20)
    val username: String,
    @field:NotBlank
    @field:Min(4)
    @field:Max(63)
    val password: String,
    @field:Email
    @field:NotBlank
    val email: String,
    val userRole: UserRoleDto,
    val activationTime: ZonedDateTime?,
    val isActivated: Boolean,
    val activationCode: String? = null,
    override var id: Long = 0,
    override val createdDate: ZonedDateTime,
    override var modifiedDate: ZonedDateTime,
) : BaseLongAuditDto(id, createdDate, modifiedDate)