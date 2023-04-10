package com.rafael.mardom.features.pokedex.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val description: String,
    val height: Double,
    val weight: Double,
    val types: List<String>,
    val stats: List<PokemonStats>,
    val sprites: PokemonSprite,
)

data class PokemonStats(
    val name: String,
    val base: Int
)

data class PokemonSprite(
    val frontDefault: String,
    val frontShiny: String,
    val backDefault: String,
    val backShiny: String,
)