package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.entity.SystemSettings

interface SystemSettingsService {

    fun getSettingById(id: String): String?

    fun getSettingsByIds(ids: Iterable<String>): Collection<String?>

    fun save(settings: SystemSettings): SystemSettings

    fun putNewSetting(id: String, value: String)

    fun getDefaultUserGroupId(): Long
}