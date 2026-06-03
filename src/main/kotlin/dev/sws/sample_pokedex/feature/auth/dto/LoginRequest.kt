package dev.sws.sample_pokedex.feature.auth.dto

data class LoginRequest(
    val username: String,
    val password: String
)