package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.entity.Permission
import dev.talosdx.cms.model.repository.PermissionRepository
import dev.talosdx.cms.model.service.prototype.PermissionService
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Service
class PermissionServiceImpl(
    val repository: PermissionRepository,
) : PermissionService {
    private val log = KotlinLogging.logger {}

    @Transactional
    override fun registerPermission(permission: Permission): Permission? {
        if (permission.id == null || !repository.existsById(permission.id!!)) {
            return repository.save(permission)
        }
        return null
    }

    @Transactional
    override fun registerPermissions(allComponentPermissions: MutableSet<Permission>) {
        allComponentPermissions.forEach { registerPermission(it) }
        repository.flush()
    }

    @Transactional
    override fun updatePermission(id: String, nameIn: String?, descIn: String?): Boolean {
        if (nameIn != null || descIn != null) {
            val permission = repository.getReferenceById(id)

            nameIn?.let { permission.name = it }
            descIn?.let { permission.description = it }
            return true
        }
        return false
    }

    override fun hasPermission(
        authentication: Authentication,
        targetDomainObject: Any?,
        permission: Any,
    ): Boolean {
        if (authentication.principal == null) return false

        val user = authentication.principal

        if (user is UserDetails) {
            val authorities = user.authorities as Collection<*>
            return authorities.contains(permission) && !authorities.contains("-$permission")
        }
        return false
    }


    override fun hasPermission(
        authentication: Authentication,
        targetId: Serializable?,
        targetType: String,
        permission: Any,
    ) = hasPermission(authentication, targetId, permission)
}