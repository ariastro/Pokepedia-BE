package dev.sws.sample_pokedex.controller

import dev.sws.sample_pokedex.core.response.BaseResponse
import dev.sws.sample_pokedex.dto.PokemonDetailDto
import dev.sws.sample_pokedex.dto.PokemonRequest
import dev.sws.sample_pokedex.dto.PokemonDto
import dev.sws.sample_pokedex.service.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/pokemon")
class PokemonController(val pokemonService: PokemonService) {

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(required = false) search: String?
    ): BaseResponse<List<PokemonDto>> {

        val (data, meta) = pokemonService.getAllPokemon(page, size, search)

        return BaseResponse(
            success = true,
            message = "OK",
            data = data,
            meta = meta
        )
    }

    @GetMapping("/{identifier}")
    fun getPokemonDetailsById(@PathVariable identifier: String): BaseResponse<PokemonDetailDto> {
        val isNumber = identifier.all { it.isDigit() }

        val data = if (isNumber) {
            val id = identifier.toInt()
            pokemonService.getPokemonDetails(id)
        } else {
            pokemonService.getPokemonDetails(identifier)
        }

        return BaseResponse(
            success = true,
            message = "OK",
            data = data
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PokemonRequest): BaseResponse<PokemonDto> {
        return BaseResponse(
            success = true,
            message = "OK",
            data = pokemonService.addPokemon(request)
        )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody request: PokemonRequest): BaseResponse<PokemonDto> {
        val data = pokemonService.updatePokemon(id, request)
        return BaseResponse(
            success = true,
            message = "OK",
            data = data
        )
    }

    @DeleteMapping
    fun delete(id: Int): BaseResponse<Nothing> {
        pokemonService.deletePokemonById(id)
        return BaseResponse(
            success = true,
            message = "Pokemon deleted successfully",
        )
    }

}