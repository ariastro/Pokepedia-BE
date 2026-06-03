package dev.sws.sample_pokedex.feature.auth.service

import dev.sws.sample_pokedex.feature.auth.security.JwtUtil
import dev.sws.sample_pokedex.feature.auth.dto.LoginRequest
import dev.sws.sample_pokedex.feature.auth.dto.LoginResponse
import dev.sws.sample_pokedex.feature.auth.repository.AdminRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun login(request: LoginRequest): LoginResponse {
        val admin = adminRepository.findByUsername(request.username)
            ?: throw RuntimeException("Invalid username or password")

        if (!passwordEncoder.matches(request.password, admin.password)) {
            throw RuntimeException("Invalid username or password")
        }

        val token = jwtUtil.generateToken(admin.username)

        return LoginResponse(token)
    }
}