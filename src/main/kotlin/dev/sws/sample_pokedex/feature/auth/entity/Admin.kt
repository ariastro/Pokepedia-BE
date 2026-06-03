package dev.sws.sample_pokedex.feature.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "admin_users")
class Admin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "username", unique = true, nullable = false)
    var username: String,
    @Column(name = "password", nullable = false)
    var password: String,
)