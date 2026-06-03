package dev.sws.sample_pokedex.feature.pokemon.dto

data class PhysicalTraitsDto(
    val description: String,
    val height: Double,
    val weight: Double,
    val speciesCategory: String
)