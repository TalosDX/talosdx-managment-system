package dev.talosdx.cms.model.service

import dev.talosdx.cms.model.dto.UserRegistrationDto
import dev.talosdx.cms.model.entity.User
import dev.talosdx.cms.model.entity.UserRole
import dev.talosdx.cms.model.repository.UserGroupRepository
import dev.talosdx.cms.model.repository.UserRepository
import dev.talosdx.cms.model.service.prototype.SystemSettingsService
import dev.talosdx.cms.model.service.prototype.UserService
import dev.talosdx.cms.util.extensions.toEntity
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@Transactional
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userGroupRepository: UserGroupRepository,
    private val systemSettings: SystemSettingsService,
) : UserService {
    private val log = KotlinLogging.logger {}


    override fun existsUserByTelegramUserId(userId: Long) =
        userRepository.existsByTelegramUserId(userId)

    override fun existsByUsernameIgnoreCase(username: String) =
        userRepository.existsByUsernameIgnoreCase(username)

    override fun createUser(user: UserRegistrationDto) =
        userRepository.save(user.toEntity(getDefaultUserGroup()))

    override fun updateUser(user: User): Boolean =
        if (user.id != null) {
            userRepository.save(user)
            true
        } else
            false


    override fun blockUser(
        blockingReasonIn: String,
        userId: Long,
        userIn: User?,
        blockingUntilDateIn: ZonedDateTime?,
    ) = if (userId != 0L || userIn?.id != 0L) {

        val id: Long = userId
        userRepository.getReferenceById(id).apply {
            isBlocked = true
            blockingDate = ZonedDateTime.now()
            blockingUntilDate = blockingUntilDateIn
            blockingReason = blockingReasonIn
        }
        true
    } else false

    override fun getDefaultUserGroup() = userGroupRepository.getReferenceById(systemSettings.getDefaultUserGroupId())

    override fun loadUserByUsername(username: String?): User {
        log.trace { "loadUserByUsername: $username" }
        if (username == null)
            throw UsernameNotFoundException("Field username is empty")

        val user = (userRepository.findByUsernameIgnoreCase(username)
            ?: throw UsernameNotFoundException("User not found"))

        log.trace { "loadUserByUsername: $username, user: $user" }
        return user
    }

    override fun createGroup(userRole: UserRole): UserRole = userGroupRepository.save(userRole)
}