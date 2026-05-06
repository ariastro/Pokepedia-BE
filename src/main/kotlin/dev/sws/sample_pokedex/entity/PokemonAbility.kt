package dev.sws.sample_pokedex.entity

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_ability")
class PokemonAbility(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "name")
    var name: String,
    @Column(name = "is_hidden")
    var isHidden: Boolean = false,
    @ManyToOne
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var pokemon: Pokemon? = null
)