package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.entity.SystemSettings
import dev.talosdx.cms.model.repository.SystemSettingsRepository
import dev.talosdx.cms.model.service.prototype.SystemSettingsService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SystemSettingsServiceImpl(
    val repository: SystemSettingsRepository,
) : SystemSettingsService {
    private val log = KotlinLogging.logger {}


    override fun getSettingById(id: String) = repository.findValueById(id)

    override fun getSettingsByIds(ids: Iterable<String>) = repository.findValuesByIds(ids)

    override fun save(settings: SystemSettings) = repository.save(settings)


    override fun putNewSetting(id: String, value: String) {
        if (!repository.existsById(id)) {
            repository.save(SystemSettings(id, value))
        }
    }

    override fun getDefaultUserGroupId(): Long =
        repository.getReferenceById("system.usergroup.defaultGroupId")
            .value
            .toLong()
}