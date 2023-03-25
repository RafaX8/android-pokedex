package com.rafael.mardom.features.pokedex.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val stats: List<PokemonStats>
)

data class PokemonStats(
    val name: String,
    val base: Int
)