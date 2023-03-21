package dev.talosdx.cms.model.dto

import dev.talosdx.cms.model.dto.prototype.BaseLongAuditDto
import dev.talosdx.cms.model.entity.ContentType
import java.time.ZonedDateTime

class ContentDto(
    val name: String,
    val shortDescription: String,
    val text: String,
    val author: UserDto,
    val contentType: ContentType = ContentType.MATERIAL,
    val attachment: dev.talosdx.cms.model.entity.Attachment? = null,
    override val createdDate: ZonedDateTime,
    override var modifiedDate: ZonedDateTime,
    id: Long? = null,
) : BaseLongAuditDto(id, createdDate, modifiedDate) {
}