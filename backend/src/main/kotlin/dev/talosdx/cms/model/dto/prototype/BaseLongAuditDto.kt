package dev.talosdx.cms.model.dto.prototype

import java.time.ZonedDateTime

abstract class BaseLongAuditDto(
    id: Long?,
    createdDate: ZonedDateTime,
    modifiedDate: ZonedDateTime,
) : BaseAuditDto<Long>(id, createdDate, modifiedDate)