package com.rafael.mardom.features.pokedex.data.remote.api

import com.rafael.mardom.features.pokedex.domain.Pokemon
import com.rafael.mardom.features.pokedex.domain.PokemonSprite
import com.rafael.mardom.features.pokedex.domain.PokemonStats

fun PokemonApiModel.toDomain() = Pokemon(
    id = this.id,
    name = this.name,
    description = this.description[0].toDomain(),
    height = this.height,
    weight = this.weight,
    types = this.types.map {
        it.toDomain()
    },
    stats = this.stats.map {
        it.toDomain()
    },
    sprites = this.sprites.toDomain()
)

private fun PokemonEntryApiModel.toDomain(): String = this.entry[0].description
private fun PokemonTypesApiModel.toDomain(): String = this.type.name
private fun PokemonStatsApiModel.toDomain() = PokemonStats(
    name = this.stat.name,
    base = this.base
)
private fun PokemonSpriteApiModel.toDomain() = PokemonSprite(
    front_default = this.front_default,
    front_shiny = this.front_shiny,
    back_default = this.back_default,
    back_shiny = this.back_shiny,
)