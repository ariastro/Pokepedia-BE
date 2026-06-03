package dev.sws.pokepedia.feature.auth.dto

data class LoginRequest(
    val username: String,
    val password: String
)