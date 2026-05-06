package dev.sws.sample_pokedex.dto

data class EvolutionRequest(
    val evolvedPokemonNumber: Int,
    val triggerMethod: String,
    val triggerValue: String
)
