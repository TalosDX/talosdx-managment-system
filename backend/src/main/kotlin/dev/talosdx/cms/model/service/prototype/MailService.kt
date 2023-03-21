package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.dto.MailDto

interface MailService {
    fun sendMail(mail: MailDto): Boolean
}