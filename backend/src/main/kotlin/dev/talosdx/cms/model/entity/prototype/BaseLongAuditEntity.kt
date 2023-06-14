package dev.talosdx.cms.model.entity.prototype

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseLongAuditEntity(
    @Id
    @Column(name = "id", nullable = false)
    open var id: Long = 0,
) : BaseAuditEntity() {
    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseLongAuditEntity

        return this.id == other.id
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}