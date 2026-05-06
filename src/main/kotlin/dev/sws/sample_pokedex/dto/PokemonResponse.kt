package dev.sws.sample_pokedex.dto

data class PokemonResponse(
    val pokemonNumber: Int,
    val id: Long,
    val name: String,
    val types: List<String>,
    val description: String
)