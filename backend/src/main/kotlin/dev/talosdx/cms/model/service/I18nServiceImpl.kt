package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.entity.I18n
import dev.talosdx.cms.model.repository.I18nRepository
import dev.talosdx.cms.model.service.prototype.I18nService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class I18nServiceImpl(
    val repository: I18nRepository,
    val languageService: LanguageServiceImpl,
) : I18nService {
    private val log = KotlinLogging.logger {}


    override fun getTranslation(languageId: String, key: String) =
        repository.findOnlyTranslation(languageId, key)

    override fun getEntityTranslation(languageId: String, key: String) =
        repository.findByKeyAndLanguageId(languageId, key)

    override fun addTranslation(i18n: I18n) =
        if (i18n.id == null || !repository.existsByKeyAndLanguageId(i18n.language.id!!, i18n.key))
            repository.save(i18n)
        else
            null

    override fun updateTranslation(languageId: String, key: String, translation: String) =
        repository.findByKeyAndLanguageId(languageId, key)?.let {
            it.translation = translation
            repository.save(it)
        }

    override fun updateTranslation(i18n: I18n) =
        repository.save(i18n)

}