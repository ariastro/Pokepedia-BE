package dev.sws.pokepedia.feature.pokemon.repository

import dev.sws.pokepedia.feature.pokemon.entity.Pokemon
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository: JpaRepository<Pokemon, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Pokemon>
    fun findByPokemonNumber(pokemonNumber: Int): Pokemon?
    fun findByNameIgnoreCase(pokemon: String): Pokemon?
    @Query("SELECT COUNT(e) FROM Pokemon p JOIN p.evolutions e")
    fun countAllEvolutions(): Long
}