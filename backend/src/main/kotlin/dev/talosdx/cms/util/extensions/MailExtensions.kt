package dev.talosdx.cms.util.extensions

import dev.talosdx.cms.model.dto.MailDto
import dev.talosdx.cms.model.entity.Mail

fun MailDto.toEntity() = Mail(
    toEmails,
    fromEmail,
    header,
    body,
    toUsers,
    fromUser,
    isSent
)

fun Mail.toDto() = MailDto(
    toEmails,
    fromEmail,
    header,
    body,
    toUsers,
    fromUser,
    isSent
)
