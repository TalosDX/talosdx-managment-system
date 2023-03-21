package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.entity.CMSComponent
import dev.talosdx.cms.model.repository.CMSComponentRepository
import dev.talosdx.cms.model.service.prototype.CMSComponentService
import dev.talosdx.cms.model.service.prototype.PermissionService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CMSComponentServiceImpl(
    val repository: CMSComponentRepository,
    val permissionService: PermissionService,
) : CMSComponentService {
    private val log = KotlinLogging.logger {}

    override fun registerComponent(component: CMSComponent): CMSComponent? =
        if (!repository.existsById(component.id!!)) {
            permissionService.registerPermissions(component.permissions)
            val savedComponent = repository.save(component)
            savedComponent
        } else
            null

}