package dev.sws.sample_pokedex.feature.auth.security

import dev.sws.sample_pokedex.exception.UnauthorizedException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

//@Component
//class ApiKeyInterceptor(
//    @Value("\${api.security.key}") private val apiKey: String
//): HandlerInterceptor {
//
//    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        if (request.method == "GET") {
//            val requestKey = request.getHeader("X-API-KEY")
//
//            if (requestKey == apiKey) {
//                return true
//            }
//
//            throw UnauthorizedException("Invalid or missing X-API-KEY header to read Pokedex data.")
//        }
//
//        return true
////        throw UnauthorizedException("Admin authentication is required to modify Pokedex data.")
//    }
//
//}