package dev.talosdx.cms.model.dto.prototype

import dev.talosdx.cms.util.serializer.KZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
abstract class BaseAuditDto<T>(
    override var id: T,

    @Serializable(with = KZonedDateTimeSerializer::class)
    open val createdDate: ZonedDateTime,

    @Serializable(with = KZonedDateTimeSerializer::class)
    open var modifiedDate: ZonedDateTime,
) : BaseDto<T>()

