package dev.sws.sample_pokedex.feature.pokemon.dto

data class EvolutionDto(
    val evolvesToPokemonNumber: Int?,
    val evolvesToName: String,
    val evolvesToImageUrl: String?,
    val triggerMethod: String,
    val triggerValue: String
)