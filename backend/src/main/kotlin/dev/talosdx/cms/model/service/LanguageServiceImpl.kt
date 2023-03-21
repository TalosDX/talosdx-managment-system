package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.entity.Language
import dev.talosdx.cms.model.repository.LanguageRepository
import dev.talosdx.cms.model.service.prototype.LanguageService
import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LanguageServiceImpl(
    private val repository: LanguageRepository,
) : LanguageService {
    private val log = KotlinLogging.logger {}


   override  fun getLanguage(languageId: String) =
        repository.findByIdOrNull(languageId)

    override fun addLanguage(language: Language) =
        if (language.id == null || !repository.existsById(language.id!!))
            repository.save(language)
        else
            null

    override fun updateLanguage(language: Language) =
        repository.save(language)

    override fun findDefaultLanguage(): Language {
        return repository.findDefaultLanguage()
    }
}
