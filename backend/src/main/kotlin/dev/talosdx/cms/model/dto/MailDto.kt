package dev.talosdx.cms.model.dto

import dev.talosdx.cms.model.dto.prototype.BaseLongAuditDto
import dev.talosdx.cms.model.entity.User
import java.time.ZonedDateTime

data class MailDto(
    val toEmails: MutableList<String>,
    val fromEmail: String,
    val header: String,
    val body: String,
    val toUsers: MutableList<User>,
    val fromUser: User?,
    var isSent: Boolean,
    override var id: Long? = null,
    override val createdDate: ZonedDateTime = ZonedDateTime.now(),
    override var modifiedDate: ZonedDateTime = ZonedDateTime.now(),
) : BaseLongAuditDto(id, createdDate, modifiedDate) {
    constructor(
        toEmails: MutableList<String>,
        fromEmail: String,
        header: String,
        body: String,
        toUsers: MutableList<User>,
    ) : this(toEmails, fromEmail, header, body, toUsers, null, false)
}