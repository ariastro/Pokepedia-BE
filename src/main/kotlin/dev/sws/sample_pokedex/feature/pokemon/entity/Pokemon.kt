package dev.sws.sample_pokedex.feature.pokemon.entity

import jakarta.persistence.*

@Entity
@Table(name = "pokemon")
class Pokemon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "pokemon_number")
    var pokemonNumber: Int,
    @Column(name = "name")
    var name: String,
    @Column(name = "type")
    var type: String,
    @Column(name = "type_2")
    var type2: String? = null,
    @Column(name = "image_url")
    var imageUrl: String,
    @Column(name = "generation")
    var generation: Int = 1,
    @OneToOne(mappedBy = "pokemon", cascade = [CascadeType.ALL])
    var detail: PokemonDetail? = null,
    @OneToMany(mappedBy = "pokemon", cascade = [CascadeType.ALL], orphanRemoval = true)
    var abilities: MutableList<PokemonAbility> = mutableListOf(),
    @OneToMany(mappedBy = "basePokemon", cascade = [CascadeType.ALL], orphanRemoval = true)
    var evolutions: MutableList<PokemonEvolution> = mutableListOf(),
)