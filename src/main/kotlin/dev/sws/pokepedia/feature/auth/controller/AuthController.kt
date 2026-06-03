package dev.sws.pokepedia.feature.auth.controller

import dev.sws.pokepedia.core.response.BaseResponse
import dev.sws.pokepedia.feature.auth.dto.LoginRequest
import dev.sws.pokepedia.feature.auth.dto.LoginResponse
import dev.sws.pokepedia.feature.auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): BaseResponse<LoginResponse> {
        val loginResponse = authService.login(request)

        return BaseResponse(
            success = true,
            message = "Login successful",
            data = loginResponse,
        )
    }
}