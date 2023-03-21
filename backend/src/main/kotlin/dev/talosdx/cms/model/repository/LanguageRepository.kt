package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository : JpaRepository<Language, String> {


    fun findDefaultLanguage(): Language {
        return findByIsDefaultIsTrueAndIsEnabledIsTrue()
    }

    @Suppress("SpringDataMethodInconsistencyInspection")
    fun findByIsDefaultIsTrueAndIsEnabledIsTrue(): Language
}