package dev.talosdx.cms.model.entity.prototype

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@AttributeOverride(name = "id", column = Column(name = "id"))
abstract class UUIDAuditEntity(
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    open var id: UUID?,
): BaseAuditEntity() {
    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseLongAuditEntity

        return this.id != null && this.id!!.equals(other.id)
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}