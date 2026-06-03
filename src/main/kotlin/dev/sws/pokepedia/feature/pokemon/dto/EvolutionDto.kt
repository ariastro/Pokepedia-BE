package dev.sws.pokepedia.feature.pokemon.dto

data class EvolutionDto(
    val evolvesToPokemonNumber: Int?,
    val evolvesToName: String,
    val evolvesToImageUrl: String?,
    val triggerMethod: String,
    val triggerValue: String
)