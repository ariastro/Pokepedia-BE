package dev.sws.sample_pokedex.feature.pokemon.entity

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_evolution")
class PokemonEvolution(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "evolution_trigger")
    var evolutionTrigger: String,
    @Column(name = "trigger_value")
    var triggerValue: String,
    @ManyToOne
    @JoinColumn(name = "base_pokemon_id", referencedColumnName = "id")
    var basePokemon: Pokemon? = null,
    @ManyToOne
    @JoinColumn(name = "evolved_pokemon_id", referencedColumnName = "id")
    var evolvedPokemon: Pokemon? = null
)