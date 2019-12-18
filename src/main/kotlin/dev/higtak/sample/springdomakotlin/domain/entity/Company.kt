package dev.higtak.sample.springdomakotlin.domain.entity

import org.seasar.doma.*
import java.time.LocalDateTime

@Entity(immutable = true)
@Table(name = "companies")
data class Company(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String,
        val location: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)