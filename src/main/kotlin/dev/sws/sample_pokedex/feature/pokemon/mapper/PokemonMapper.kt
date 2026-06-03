package dev.sws.sample_pokedex.feature.pokemon.mapper

import dev.sws.sample_pokedex.feature.pokemon.dto.AbilityDto
import dev.sws.sample_pokedex.feature.pokemon.dto.EvolutionDto
import dev.sws.sample_pokedex.feature.pokemon.dto.PhysicalTraitsDto
import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonDetailDto
import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonRequest
import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonDto
import dev.sws.sample_pokedex.feature.pokemon.dto.StatsDto
import dev.sws.sample_pokedex.feature.pokemon.entity.Pokemon
import dev.sws.sample_pokedex.feature.pokemon.entity.PokemonAbility
import dev.sws.sample_pokedex.feature.pokemon.entity.PokemonDetail

fun PokemonRequest.toEntity(): Pokemon {
    val newPokemon = Pokemon(
        pokemonNumber = pokemonNumber,
        name = name,
        type = type,
        type2 = type2,
        imageUrl = imageUrl,
        generation = generation
    )

    val newDetail = PokemonDetail(
        description = description,
        height = height,
        weight = weight,
        speciesCategory = speciesCategory,
        hp = hp,
        attack = attack,
        defense = defense,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed,
        pokemon = newPokemon
    )

    val mappedAbilities = this.abilities.map { abilityReq ->
        PokemonAbility(
            name = abilityReq.name,
            isHidden = abilityReq.isHidden,
            pokemon = newPokemon
        )
    }.toMutableList()

    newPokemon.abilities = mappedAbilities
    newPokemon.detail = newDetail

    return newPokemon
}

fun Pokemon.toResponse(): PokemonDto {
    val typeList = mutableListOf(type)
    type2?.let { typeList.add(it) }

    return PokemonDto(
        id = pokemonNumber,
        name = name,
        types = typeList,
        imageUrl = imageUrl,
        generation = generation,
    )
}

fun Pokemon.toDetailResponse(): PokemonDetailDto {
    val typeList = mutableListOf(type)
    type2?.let { typeList.add(it) }

    val mappedPhysicalTraits = detail?.let {
        PhysicalTraitsDto(
            description = it.description,
            height = it.height,
            weight = it.weight,
            speciesCategory = it.speciesCategory
        )
    }

    val mappedStats = detail?.let {
        StatsDto(
            hp = it.hp,
            attack = it.attack,
            defense = it.defense,
            specialAttack = it.specialAttack,
            specialDefense = it.specialDefense,
            speed = it.speed
        )
    }

    val mappedAbilities = abilities.map { ability ->
        AbilityDto(
            name = ability.name,
            isHidden = ability.isHidden
        )
    }

    val mappedEvolutions = evolutions.mapNotNull { evolution ->
        evolution.evolvedPokemon?.let { evolved ->
            EvolutionDto(
                evolvesToPokemonNumber = evolved.pokemonNumber,
                evolvesToName = evolved.name,
                evolvesToImageUrl = evolved.imageUrl,
                triggerMethod = evolution.evolutionTrigger,
                triggerValue = evolution.triggerValue
            )
        }
    }

    return PokemonDetailDto(
        id = id,
        pokemonNumber = pokemonNumber,
        name = name,
        types = typeList,
        imageUrl = imageUrl,
        generation = generation,
        physicalTraits = mappedPhysicalTraits,
        stats = mappedStats,
        abilities = mappedAbilities,
        evolutions = mappedEvolutions
    )
}