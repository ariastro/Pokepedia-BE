package dev.sws.sample_pokedex.exception

import dev.sws.sample_pokedex.core.response.BaseResponse
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

}