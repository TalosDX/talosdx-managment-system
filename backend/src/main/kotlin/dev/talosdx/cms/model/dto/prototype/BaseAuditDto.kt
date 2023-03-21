package dev.talosdx.cms.model.dto.prototype

import java.time.ZonedDateTime

abstract class BaseAuditDto<T>(
    override var id: T?,
    open val createdDate: ZonedDateTime,
    open var modifiedDate: ZonedDateTime,
) : BaseDto<T>()