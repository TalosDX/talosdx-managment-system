package dev.talosdx.cms.model.service.prototype

import dev.talosdx.cms.model.entity.CMSComponent

interface CMSComponentService {
    fun registerComponent(component: CMSComponent): CMSComponent?
}