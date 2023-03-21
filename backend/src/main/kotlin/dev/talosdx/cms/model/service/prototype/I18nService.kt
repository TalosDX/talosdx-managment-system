package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.entity.I18n

interface I18nService {

    fun getTranslation(languageId: String, key: String): String?

    fun getEntityTranslation(languageId: String, key: String): I18n?

    fun addTranslation(i18n: I18n): I18n?

    fun updateTranslation(languageId: String, key: String, translation: String): I18n?

    fun updateTranslation(i18n: I18n): I18n

}