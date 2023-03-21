package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.CMSComponent
import org.springframework.data.jpa.repository.JpaRepository

interface CMSComponentRepository : JpaRepository<CMSComponent, String>