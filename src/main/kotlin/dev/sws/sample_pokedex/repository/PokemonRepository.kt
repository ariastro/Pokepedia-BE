package dev.sws.sample_pokedex.repository

import dev.sws.sample_pokedex.entity.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository: JpaRepository<Pokemon, Long> {
    fun findByNameContainsIgnoreCase(name: String): List<Pokemon>
}