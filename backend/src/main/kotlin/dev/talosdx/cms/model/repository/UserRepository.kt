package dev.talosdx.cms.model.repository

import dev.talosdx.cms.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsernameIgnoreCase(username: String): Boolean

    fun findByUsernameIgnoreCase(username: String): User?

    fun existsByTelegramUserId(userId: Long): Boolean {
        return false
    }
}