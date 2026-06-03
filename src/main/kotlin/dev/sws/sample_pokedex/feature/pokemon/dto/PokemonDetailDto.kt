package dev.sws.sample_pokedex.feature.pokemon.dto

data class PokemonDetailDto(
    val id: Long,
    val pokemonNumber: Int?,
    val name: String,
    val types: List<String>,
    val imageUrl: String?,
    val generation: Int,
    val physicalTraits: PhysicalTraitsDto?,
    val stats: StatsDto?,
    val abilities: List<AbilityDto>,
    val evolutions: List<EvolutionDto>
)
