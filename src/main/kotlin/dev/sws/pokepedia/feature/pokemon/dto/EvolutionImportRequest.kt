package dev.sws.pokepedia.feature.pokemon.dto

data class EvolutionImportRequest(
    val fromPokemonNumber: Int,
    val toPokemonNumber: Int,
    val evolutionTrigger: String,
    val triggerValue: String
)