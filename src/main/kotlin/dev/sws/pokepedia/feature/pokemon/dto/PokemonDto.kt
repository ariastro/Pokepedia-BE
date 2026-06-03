package dev.sws.pokepedia.feature.pokemon.dto

data class PokemonDto(
    val id: Int,
    val name: String,
    val types: List<String>,
    val imageUrl: String,
    val generation: Int
)