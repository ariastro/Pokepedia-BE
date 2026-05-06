package dev.sws.sample_pokedex.dto

data class PokemonDto(
    val id: Long,
    val pokemonNumber: Int,
    val name: String,
    val types: List<String>,
    val imageUrl: String,
    val generation: Int
)