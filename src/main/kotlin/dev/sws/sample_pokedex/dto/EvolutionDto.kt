package dev.sws.sample_pokedex.dto

data class EvolutionDto(
    val evolvesToPokemonNumber: Int?,
    val evolvesToName: String,
    val evolvesToImageUrl: String?,
    val triggerMethod: String,
    val triggerValue: String
)