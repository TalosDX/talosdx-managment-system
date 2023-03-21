package dev.talosdx.cms.model.entity.prototype

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.TimeZoneStorage
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAuditEntity {

    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    @TimeZoneStorage
    open lateinit var createdDate: ZonedDateTime

    @LastModifiedDate
    @Column(name = "modified_date", updatable = false, nullable = false)
    @TimeZoneStorage
    open lateinit var modifiedDate: ZonedDateTime
}