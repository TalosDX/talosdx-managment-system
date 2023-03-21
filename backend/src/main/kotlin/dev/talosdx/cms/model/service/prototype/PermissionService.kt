package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.entity.Permission
import org.springframework.security.access.PermissionEvaluator

interface PermissionService : PermissionEvaluator {

    fun registerPermission(permission: Permission): Permission?

    fun registerPermissions(allComponentPermissions: MutableSet<Permission>)

    fun updatePermission(id: String, nameIn: String? = null, descIn: String? = null): Boolean

}