package dev.talosdx.cms.model.dto

import dev.talosdx.cms.model.dto.prototype.BaseDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRoleDto(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    override var id: Long? = null,
) : BaseDto<Long>() {}
