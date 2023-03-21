package dev.talosdx.cms.config.startup

import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.entity.CMSComponent
import dev.talosdx.cms.model.entity.Language
import dev.talosdx.cms.model.entity.Permission
import dev.talosdx.cms.model.entity.UserRole
import dev.talosdx.cms.model.service.*
import dev.talosdx.cms.model.service.prototype.SystemSettingsService
import dev.talosdx.cms.model.service.prototype.UserRegistrationService
import dev.talosdx.cms.model.service.prototype.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
@Transactional
class CommandLineAppStartupRunner(
    val userRegistrationService: UserRegistrationService,
    val userService: UserService,
    val componentService: CMSComponentServiceImpl,
    val languageService: LanguageServiceImpl,
    val systemSettingsService: SystemSettingsService,
) : CommandLineRunner {


    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val defaultUserRole = userService.createGroup(UserRole("users", "группа пользователей по умолчанию"))

        systemSettingsService.putNewSetting("system.usergroup.defaultGroupId", defaultUserRole.id!!.toString())

        componentService.registerComponent(
            CMSComponent("system.user")
                .apply {
                    permissions.addAll(
                        setOf(
                            Permission("user.auth"),
                            Permission("user.create"),
                            Permission("user.update"),
                            Permission("user.delete"),
                            Permission("user.ban"),
                        )
                    )
                }
        )

        Locale.getISOLanguages()
            .map { Language(it) }
            .forEach { languageService.addLanguage(it) }




        if (!userService.existsByUsernameIgnoreCase("admin")) {
            userRegistrationService.registerUser(
                UserRegistrationDto(
                    "admin",
                    "admin",
                    "admin@localhost"
                ), true
            )
        }
    }

}