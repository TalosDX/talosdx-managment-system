package dev.talosdx.cms.model.entity.prototype

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class UUIDAuditEntity(
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    override var id: UUID?,
) : BaseIdAuditEntity<UUID>()