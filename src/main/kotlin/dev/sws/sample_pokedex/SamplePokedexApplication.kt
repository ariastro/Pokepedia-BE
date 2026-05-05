package dev.sws.sample_pokedex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SamplePokedexApplication

fun main(args: Array<String>) {
    runApplication<SamplePokedexApplication>(*args)
}
