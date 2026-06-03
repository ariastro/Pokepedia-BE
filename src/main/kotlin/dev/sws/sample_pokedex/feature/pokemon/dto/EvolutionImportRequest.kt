package dev.sws.sample_pokedex.feature.pokemon.dto

data class EvolutionImportRequest(
    val fromPokemonNumber: Int,
    val toPokemonNumber: Int,
    val evolutionTrigger: String,
    val triggerValue: String
)