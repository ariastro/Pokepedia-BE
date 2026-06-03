package dev.sws.pokepedia.exception

import dev.sws.pokepedia.core.response.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException::class)
    fun handlePokemonNotFoundException(ex: PokemonNotFoundException): ResponseEntity<BaseResponse<Nothing>> {
        val response = BaseResponse(
            success = false,
            message = ex.message ?: "Pokemon not found",
            data = null
        )
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException): ResponseEntity<BaseResponse<Nothing>> {
        val response = BaseResponse(
            success = false,
            message = ex.message ?: "Unauthorized",
            data = null
        )
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<BaseResponse<Nothing>> {
        val response = BaseResponse(
            success = false,
            message = ex.message ?: "An unexpected internal server error occurred.",
            data = null
        )
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}