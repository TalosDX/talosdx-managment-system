package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.Attachment
import org.springframework.data.jpa.repository.JpaRepository

interface AttachmentRepository : JpaRepository<Attachment, Long> {}