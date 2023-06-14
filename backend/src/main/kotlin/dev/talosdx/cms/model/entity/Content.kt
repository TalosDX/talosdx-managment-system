package dev.talosdx.cms.model.entity

import dev.talosdx.cms.model.entity.prototype.BaseLongAuditEntity
import jakarta.persistence.*

@Entity
@Table(name = "content")
class Content(
    var name: String,
    var shortDescription: String,
    var text: String,
    @OneToOne
    val author: User,
    @Enumerated(EnumType.STRING)
    var contentType: ContentType = ContentType.MATERIAL,
    @OneToOne
    var attachment: Attachment? = null,
    id: Long = 0,
) : BaseLongAuditEntity(id)


