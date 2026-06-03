package dev.sws.pokepedia.feature.pokemon.dto

data class EvolutionRequest(
    val evolvedPokemonNumber: Int,
    val triggerMethod: String,
    val triggerValue: String
)
