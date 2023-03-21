package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.SystemSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SystemSettingsRepository : JpaRepository<SystemSettings, String> {

    @Query("SELECT settings.value from SystemSettings settings WHERE settings.id=?1")
    fun findValueById(id: String): String?

    @Query("SELECT settings.value from SystemSettings settings WHERE settings.id in ?1")
    fun findValuesByIds(id: Iterable<String>): Collection<String?>
}