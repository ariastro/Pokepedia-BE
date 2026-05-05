package dev.sws.sample_pokedex.dto

data class PokemonRequest(
    val name: String,
    val type: String,
    val type2: String?,
    val description: String
)