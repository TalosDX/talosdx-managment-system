package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.entity.Language

interface LanguageService {
    fun getLanguage(languageId: String): Language?
    fun addLanguage(language: Language): Language?
    fun updateLanguage(language: Language): Language
    fun findDefaultLanguage(): Language
}
