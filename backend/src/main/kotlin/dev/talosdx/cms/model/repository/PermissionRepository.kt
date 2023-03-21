package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : JpaRepository<Permission, String> {
}