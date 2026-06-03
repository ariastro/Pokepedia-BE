package dev.sws.sample_pokedex.feature.pokemon.service

import dev.sws.sample_pokedex.core.response.PageMeta
import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonDetailDto
import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonDto
import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonRequest
import dev.sws.sample_pokedex.feature.pokemon.entity.PokemonAbility
import dev.sws.sample_pokedex.feature.pokemon.entity.PokemonDetail
import dev.sws.sample_pokedex.feature.pokemon.entity.PokemonEvolution
import dev.sws.sample_pokedex.exception.PokemonNotFoundException
import dev.sws.sample_pokedex.feature.pokemon.dto.EvolutionImportRequest
import dev.sws.sample_pokedex.feature.pokemon.mapper.toDetailResponse
import dev.sws.sample_pokedex.feature.pokemon.mapper.toEntity
import dev.sws.sample_pokedex.feature.pokemon.mapper.toResponse
import dev.sws.sample_pokedex.feature.pokemon.repository.PokemonRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PokemonService(
    private val pokemonRepository: PokemonRepository,
) {

    fun getAllPokemon(page: Int, size: Int, search: String?): Pair<List<PokemonDto>, PageMeta> {
        val springPage = if (page > 0) page - 1 else 0

        val pageable = PageRequest.of(springPage, size, Sort.by("pokemonNumber").ascending())

        val pokemonPage = if (search.isNullOrEmpty()) {
            pokemonRepository.findAll(pageable)
        } else {
            pokemonRepository.findByNameContainingIgnoreCase(search, pageable)
        }

        val dataList = pokemonPage.content.map { it.toResponse() }

        val meta = PageMeta(
            currentPage = springPage + 1,
            totalPages = pokemonPage.totalPages,
            totalElements = pokemonPage.totalElements,
            hasNext = pokemonPage.hasNext()
        )

        return dataList to meta
    }

    fun getPokemonDetails(id: Int): PokemonDetailDto {
        val pokemon = pokemonRepository.findByPokemonNumber(id)
            ?: throw PokemonNotFoundException("Pokemon with ID $id was not found.")

        return pokemon.toDetailResponse()
    }

    fun getPokemonDetails(pokemon: String): PokemonDetailDto {
        val pokemon = pokemonRepository.findByNameIgnoreCase(pokemon)
            ?: throw PokemonNotFoundException("Pokemon $pokemon was not found.")

        return pokemon.toDetailResponse()
    }

    fun addPokemon(request: PokemonRequest): PokemonDto {
        val newPokemon = request.toEntity()

        if (request.evolutions.isNotEmpty()) {
            val mappedEvolutions = request.evolutions.mapNotNull { evoReq ->
                val targetPokemon = pokemonRepository.findByPokemonNumber(evoReq.evolvedPokemonNumber)

                if (targetPokemon != null) {
                    PokemonEvolution(
                        evolutionTrigger = evoReq.triggerMethod,
                        triggerValue = evoReq.triggerValue,
                        basePokemon = newPokemon,
                        evolvedPokemon = targetPokemon
                    )
                } else {
                    null
                }
            }.toMutableList()
            newPokemon.evolutions = mappedEvolutions
        }

        val savedEntity = pokemonRepository.save(newPokemon)
        return savedEntity.toResponse()
    }

    fun updatePokemon(id: Int, request: PokemonRequest): PokemonDto {
        val existingPokemon = pokemonRepository.findByPokemonNumber(id)
            ?: throw PokemonNotFoundException("Pokemon with ID $id was not found.")

        existingPokemon.pokemonNumber = request.pokemonNumber
        existingPokemon.name = request.name
        existingPokemon.type = request.type
        existingPokemon.type2 = request.type2
        existingPokemon.imageUrl = request.imageUrl
        existingPokemon.generation = request.generation

        if (existingPokemon.detail != null) {
            existingPokemon.detail!!.description = request.description
            existingPokemon.detail!!.height = request.height
            existingPokemon.detail!!.weight = request.weight
            existingPokemon.detail!!.speciesCategory = request.speciesCategory
        } else {
            val newDetail = PokemonDetail(
                description = request.description,
                height = request.height,
                weight = request.weight,
                speciesCategory = request.speciesCategory,
                pokemon = existingPokemon
            )
            existingPokemon.detail = newDetail
        }

        existingPokemon.abilities.clear()
        existingPokemon.abilities.addAll(
            request.abilities.map {
                PokemonAbility(name = it.name, isHidden = it.isHidden, pokemon = existingPokemon)
            }
        )

        existingPokemon.evolutions.clear()
        existingPokemon.evolutions.addAll(
            request.evolutions.mapNotNull { evoReq ->
                val targetPokemon = pokemonRepository.findByPokemonNumber(evoReq.evolvedPokemonNumber)
                if (targetPokemon != null) {
                    PokemonEvolution(
                        evolutionTrigger = evoReq.triggerMethod,
                        triggerValue = evoReq.triggerValue,
                        basePokemon = existingPokemon,
                        evolvedPokemon = targetPokemon
                    )
                } else null
            }
        )

        val updatedEntity = pokemonRepository.save(existingPokemon)
        return updatedEntity.toResponse()
    }

    fun deletePokemonById(id: Int) {
        val pokemon = pokemonRepository.findByPokemonNumber(id)
            ?: throw RuntimeException("Pokemon with number $id was not found.")

        pokemonRepository.delete(pokemon)
    }

    @Transactional
    fun linkEvolution(request: EvolutionImportRequest) {
        val basePokemon = pokemonRepository.findByPokemonNumber(request.fromPokemonNumber) ?: return
        val targetPokemon = pokemonRepository.findByPokemonNumber(request.toPokemonNumber) ?: return

        val evolutionLine = PokemonEvolution(
            basePokemon = basePokemon,
            evolvedPokemon = targetPokemon,
            evolutionTrigger = request.evolutionTrigger,
            triggerValue = request.triggerValue
        )

        basePokemon.evolutions.add(evolutionLine)
        pokemonRepository.save(basePokemon)
    }
}