package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.dto.ContentDto
import dev.talosdx.cms.model.repository.ContentRepository
import dev.talosdx.cms.model.service.prototype.ContentService
import dev.talosdx.cms.util.exception.AlreadyExistsException
import dev.talosdx.cms.util.exception.NotFoundException
import dev.talosdx.cms.util.extensions.toDto
import dev.talosdx.cms.util.extensions.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ContentServiceImpl(
    private val repository: ContentRepository,
) : ContentService {

    override fun publishContent(content: ContentDto): ContentDto =
        if (!repository.existsByNameIgnoreCase(content.name)) {
            repository.save(content.toEntity()).toDto()
        } else
            throw AlreadyExistsException("Content with than name=${content.name} already exists!")

    override fun updateContent(contentIn: ContentDto): ContentDto =
        if (repository.existsById(contentIn.id!!)) {
            val editableContent = repository.getReferenceById(contentIn.id!!)

            editableContent.name = contentIn.name
            editableContent.shortDescription = contentIn.shortDescription
            editableContent.text = contentIn.text
            editableContent.contentType = contentIn.contentType
            editableContent.attachment = contentIn.attachment
            repository.save(editableContent).toDto()
        } else
            throw NotFoundException("Content with id=${contentIn.id}")

    override fun getContent(id: Long): ContentDto =
        repository.getReferenceById(id).toDto()

    override fun getContentByName(name: String): ContentDto =
        repository.findByNameContainsIgnoreCase(name).toDto()

    override fun deleteContent(contentId: Long) = repository.deleteById(contentId)

    override fun deleteContent(content: ContentDto) = content.id?.let { repository.deleteById(it) }
}