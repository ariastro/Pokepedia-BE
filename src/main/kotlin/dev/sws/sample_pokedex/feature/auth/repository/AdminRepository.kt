package dev.sws.sample_pokedex.feature.auth.repository

import dev.sws.sample_pokedex.feature.auth.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Admin, Long> {
    fun findByUsername(username: String): Admin?
}