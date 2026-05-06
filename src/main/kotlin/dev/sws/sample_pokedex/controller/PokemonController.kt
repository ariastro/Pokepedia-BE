package dev.sws.sample_pokedex.controller

import dev.sws.sample_pokedex.core.response.BaseResponse
import dev.sws.sample_pokedex.dto.PokemonRequest
import dev.sws.sample_pokedex.dto.PokemonResponse
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
    ): BaseResponse<List<PokemonResponse>> {

        val (data, meta) = pokemonService.getAllPokemon(page, size)

        return BaseResponse(
            success = true,
            message = "OK",
            data = data,
            meta = meta
        )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): BaseResponse<PokemonResponse> {
        return BaseResponse(
            success = true,
            message = "OK",
            data = pokemonService.getPokemonById(id)
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PokemonRequest): BaseResponse<PokemonResponse> {
        return BaseResponse(
            success = true,
            message = "OK",
            data = pokemonService.addPokemon(request)
        )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: PokemonRequest): BaseResponse<PokemonResponse> {
        val data = pokemonService.updatePokemon(id, request)
        return BaseResponse(
            success = true,
            message = "OK",
            data = data
        )
    }

    @DeleteMapping
    fun delete(id: Long): BaseResponse<Nothing> {
        pokemonService.deletePokemonById(id)
        return BaseResponse(
            success = true,
            message = "Pokemon deleted successfully",
        )
    }

}