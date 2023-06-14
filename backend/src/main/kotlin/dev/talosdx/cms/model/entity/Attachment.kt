package dev.talosdx.cms.model.entity

import dev.talosdx.cms.model.entity.prototype.BaseLongAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "attachment")
class Attachment(
    @Column(name = "download_url", nullable = false)
    val downloadUrl: String,
    id: Long = 0,
) : BaseLongAuditEntity(id) {
}