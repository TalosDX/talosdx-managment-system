package dev.talosdx.cms.model.entity

import dev.talosdx.cms.model.entity.prototype.BaseLongAuditEntity
import jakarta.persistence.*
import org.springframework.context.annotation.Lazy

@Entity
@Table(name = "user_role")
class UserRole(
    @Column(name = "name", unique = false, nullable = false)
    val name: String,
    @Column(name = "description", unique = false, nullable = false)
    val description: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role_to_permissions",
        joinColumns = [JoinColumn(name = "user_role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permissions: MutableSet<Permission> = mutableSetOf(),
    @Lazy
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
    var users: MutableSet<User> = mutableSetOf(),
    id: Long? = null,
) : BaseLongAuditEntity(id) {
}