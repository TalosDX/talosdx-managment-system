package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.dto.ContentDto

interface ContentService {

    fun publishContent(content: ContentDto) : ContentDto

    fun updateContent(contentIn: ContentDto) : ContentDto

    fun getContent(id: Long): ContentDto

    fun getContentByName(name: String): ContentDto

    fun deleteContent(contentId: Long)

    fun deleteContent(content: ContentDto): Unit?
}

