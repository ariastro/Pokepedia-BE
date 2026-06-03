package dev.sws.pokepedia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokepediaApplication

fun main(args: Array<String>) {
    runApplication<PokepediaApplication>(*args)
}
