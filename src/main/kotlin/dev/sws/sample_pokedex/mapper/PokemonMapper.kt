package dev.sws.sample_pokedex.mapper

import dev.sws.sample_pokedex.dto.PokemonRequest
import dev.sws.sample_pokedex.dto.PokemonResponse
import dev.sws.sample_pokedex.entity.Pokemon

fun PokemonRequest.toEntity(): Pokemon {
    return Pokemon(
        name = name,
        type = type,
        type2 = type2,
        description = description
    )
}

fun Pokemon.toResponse(): PokemonResponse {
    val typeList = mutableListOf(type)
    type2?.let { typeList.add(it) }

    return PokemonResponse(
        id = id,
        name = name,
        types = typeList,
        description = description,
    )
}