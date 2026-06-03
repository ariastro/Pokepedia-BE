package dev.sws.sample_pokedex.feature.pokemon.dto

data class EvolutionRequest(
    val evolvedPokemonNumber: Int,
    val triggerMethod: String,
    val triggerValue: String
)
