package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.Content
import org.springframework.data.jpa.repository.JpaRepository


interface ContentRepository : JpaRepository<Content, Long> {

    fun findByNameIgnoreCase(name: String): Content

    fun findByNameContainsIgnoreCase(name: String): Content

    fun existsByNameIgnoreCase(name: String): Boolean
}