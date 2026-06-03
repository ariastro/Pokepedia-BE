package dev.sws.sample_pokedex.feature.pokemon.service

import dev.sws.sample_pokedex.feature.pokemon.dto.PokemonRequest
import dev.sws.sample_pokedex.feature.pokemon.repository.PokemonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import tools.jackson.core.type.TypeReference
import tools.jackson.databind.ObjectMapper

@Component
class PokemonDataInitializer(
    private val pokemonRepository: PokemonRepository,
    private val pokemonService: PokemonService,
    private val objectMapper: ObjectMapper,
) : CommandLineRunner {

    override fun run(vararg args: String) {
        // Only run if the database is completely empty
        if (pokemonRepository.count() == 0L) {
            println("📂 Database is empty. Initiating bulk Generation 1 data import...")

            try {
                val resource = ClassPathResource("pokemon_gen1.json")

                val typeRef = object : TypeReference<List<PokemonRequest>>() {}
                val pokemonList: List<PokemonRequest> = objectMapper.readValue(resource.inputStream, typeRef)

                pokemonList.forEach { request ->
                    pokemonService.addPokemon(request)
                }

                println("✅ Successfully populated database with ${pokemonList.size} Gen 1 Pokémon!")
            } catch (e: Exception) {
                println("❌ Failed to import bulk Pokémon data: ${e.message}")
                e.printStackTrace()
            }
        } else {
            println("ℹ️ Pokémon data already exists. Skipping bulk import.")
        }
    }
}