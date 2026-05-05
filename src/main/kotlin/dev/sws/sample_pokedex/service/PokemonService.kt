package dev.sws.sample_pokedex.service

import dev.sws.sample_pokedex.core.response.PaginatedResponse
import dev.sws.sample_pokedex.dto.PageMeta
import dev.sws.sample_pokedex.dto.PokemonRequest
import dev.sws.sample_pokedex.dto.PokemonResponse
import dev.sws.sample_pokedex.exception.PokemonNotFoundException
import dev.sws.sample_pokedex.mapper.toEntity
import dev.sws.sample_pokedex.mapper.toResponse
import dev.sws.sample_pokedex.repository.PokemonRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PokemonService(
    private val pokemonRepository: PokemonRepository,
) {

    fun getAllPokemon(page: Int, size: Int): Pair<List<PokemonResponse>, PageMeta> {
        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id")

        val pokemonPage = pokemonRepository.findAll(pageable)

        val dataList = pokemonPage.content.map { it.toResponse() }

        val meta = PageMeta(
            currentPage = pokemonPage.number,
            totalPages = pokemonPage.totalPages,
            totalElements = pokemonPage.totalElements,
            hasNext = pokemonPage.hasNext()
        )

        return dataList to meta
    }

    fun getPokemonById(id: Long): PokemonResponse {
        val pokemon = pokemonRepository.findByIdOrNull(id)
            ?: throw PokemonNotFoundException("Pokemon with id $id not found")

        return pokemon.toResponse()
    }

    fun addPokemon(request: PokemonRequest): PokemonResponse {
        val entityToSave = request.toEntity()
        val savedEntity = pokemonRepository.save(entityToSave)
        return savedEntity.toResponse()
    }

    fun updatePokemon(id: Long, request: PokemonRequest): PokemonResponse {
        val existingPokemon = pokemonRepository.findByIdOrNull(id) ?: throw PokemonNotFoundException("Pokemon with id $id not found")

        existingPokemon.name = request.name
        existingPokemon.type = request.type
        existingPokemon.type2 = request.type2
        existingPokemon.description = request.description

        val updatedEntity = pokemonRepository.save(existingPokemon)

        return updatedEntity.toResponse()
    }

    fun deletePokemonById(id: Long) {
        if (!pokemonRepository.existsById(id)) {
            throw PokemonNotFoundException("Pokemon with id $id not found")
        }
        pokemonRepository.deleteById(id)
    }
}