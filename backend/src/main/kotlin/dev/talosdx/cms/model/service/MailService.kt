package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.dto.MailDto
import dev.talosdx.cms.model.repository.MailRepository
import dev.talosdx.cms.model.service.prototype.MailService
import dev.talosdx.cms.util.extensions.toEntity
import mu.KotlinLogging
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MailServiceImpl(
    val mailSender: JavaMailSender,
    val mailRepository: MailRepository,
) : MailService {
    private val log = KotlinLogging.logger {}

    override fun sendMail(mail: MailDto): Boolean {
        try {
            val message = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true)

            helper.setFrom("noreply@talosdx.dev")
            helper.setTo(mail.toEmails.toTypedArray())
            helper.setSubject(mail.header)
            helper.setText(mail.body)
            mailSender.send(message)


            mail.isSent = true
            mailRepository.save(mail.toEntity())
            return true
        } catch (exception: Exception) {
            log.error(exception) { "sendMail failed mail=${mail}" }
            throw exception
        }
    }
}