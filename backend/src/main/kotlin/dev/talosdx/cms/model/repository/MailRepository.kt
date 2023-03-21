package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.Mail
import org.springframework.data.jpa.repository.JpaRepository

interface MailRepository : JpaRepository<Mail, Long> {
}