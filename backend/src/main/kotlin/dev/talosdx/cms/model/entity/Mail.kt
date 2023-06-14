package dev.talosdx.cms.model.entity

import dev.talosdx.cms.model.entity.prototype.BaseLongAuditEntity
import dev.talosdx.cms.util.converter.StringListConverter
import jakarta.persistence.*
import org.springframework.context.annotation.Lazy

@Entity
@Table(name = "mail")
class Mail(
    @Column(name = "to_emails", nullable = false)
    @Convert(converter = StringListConverter::class)
    val toEmails: MutableList<String>,
    @Column(name = "fromEmail", nullable = false)
    val fromEmail: String,
    @Column(name = "header", nullable = false)
    val header: String,
    @Column(name = "body", nullable = false)
    val body: String,
    @Lazy
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id")
    val toUsers: MutableList<User>,
    @Lazy
    @OneToOne(fetch = FetchType.LAZY)
    val fromUser: User?,
    @Column(name = "is_sent", nullable = false)
    var isSent: Boolean,
    id: Long = 0,
) : BaseLongAuditEntity(id)



