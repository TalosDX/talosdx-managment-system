package dev.talosdx.cms.model.dto

import dev.talosdx.cms.model.dto.prototype.BaseLongAuditDto
import java.time.ZonedDateTime

data class AttachmentDto(
    val downloadUrl: String,
    override val createdDate: ZonedDateTime,
    override var modifiedDate: ZonedDateTime,
    override var id: Long? = null,
) : BaseLongAuditDto(id, createdDate, modifiedDate) {
}