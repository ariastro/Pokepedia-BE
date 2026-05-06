package dev.sws.sample_pokedex.dto

data class PokemonRequest(
    val pokemonNumber: Int,
    val name: String,
    val type: String,
    val type2: String?,
    val imageUrl: String,
    val generation: Int,
    val description: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0,
    val speciesCategory: String = "",
    val abilities: List<AbilityRequest> = emptyList(),
    val evolutions: List<EvolutionRequest> = emptyList()
)