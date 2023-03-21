package dev.talosdx.cms.util.extensions

import dev.talosdx.cms.model.dto.ContentDto
import dev.talosdx.cms.model.entity.Content

fun Content.toDto() = ContentDto(
    name,
    shortDescription,
    text,
    author.toDto(),
    contentType,
    attachment,
    createdDate,
    modifiedDate,
    id
)

fun ContentDto.toEntity() = Content(
    name,
    shortDescription,
    text,
    author.toEntity(),
    contentType,
    attachment,
    id
)