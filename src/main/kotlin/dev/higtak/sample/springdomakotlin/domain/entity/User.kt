package dev.higtak.sample.springdomakotlin.domain.entity

import org.seasar.doma.*
import java.time.LocalDateTime

@Entity(immutable = true)
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val username: String,
        val fullName: String,
        val companyId: Long? = null,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)