package dev.talosdx.cms.model.entity

import dev.talosdx.cms.model.entity.prototype.BaseLongAuditEntity
import jakarta.persistence.*

@Entity
@Table(name = "i18n")
class I18n(
    id: Long? = null,
    @Column(name = "key", unique = false, nullable = false)
    var key: String,
    @ManyToOne(fetch = FetchType.EAGER)
    var language: Language,
    @Column(name = "translation", unique = false, nullable = false)
    var translation: String,
) : BaseLongAuditEntity(id) {
}



