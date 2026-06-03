package dev.sws.pokepedia.feature.pokemon.service

import dev.sws.pokepedia.feature.pokemon.dto.EvolutionImportRequest
import dev.sws.pokepedia.feature.pokemon.repository.PokemonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import tools.jackson.core.type.TypeReference
import tools.jackson.databind.ObjectMapper

@Component
@Order(2)
class EvolutionDataInitializer(
    private val pokemonRepository: PokemonRepository,
    private val pokemonService: PokemonService,
    private val objectMapper: ObjectMapper
) : CommandLineRunner {

    override fun run(vararg args: String) {
        if (pokemonRepository.count() > 0) {
            println("🔗 Linking Generation 1 evolutionary pathways...")

            try {
                val resource = ClassPathResource("pokemon_evolutions.json")
                val typeRef = object : TypeReference<List<EvolutionImportRequest>>() {}
                val evolutionList: List<EvolutionImportRequest> = objectMapper.readValue(resource.inputStream, typeRef)

                evolutionList.forEach { request ->
                    pokemonService.linkEvolution(request)
                }

                println("✅ Successfully cross-linked all Gen 1 evolution lines!")
            } catch (e: Exception) {
                println("❌ Failed to bind evolution linkages: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}