package dev.sws.sample_pokedex.feature.pokemon.dto

data class StatsDto(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int
)
