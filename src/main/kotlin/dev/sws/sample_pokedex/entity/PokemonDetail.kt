package dev.sws.sample_pokedex.entity

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_detail")
class PokemonDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "description")
    var description: String = "",
    @Column(name = "height")
    var height: Double = 0.0,
    @Column(name = "weight")
    var weight: Double = 0.0,
    @Column(name = "species_category")
    var speciesCategory: String = "",
    @Column(name = "hp")
    var hp: Int = 0,
    @Column(name = "attack")
    var attack: Int = 0,
    @Column(name = "defense")
    var defense: Int = 0,
    @Column(name = "special_attack")
    var specialAttack: Int = 0,
    @Column(name = "special_defense")
    var specialDefense: Int = 0,
    @Column(name = "speed")
    var speed: Int = 0,
    @OneToOne
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var pokemon: Pokemon? = null
)