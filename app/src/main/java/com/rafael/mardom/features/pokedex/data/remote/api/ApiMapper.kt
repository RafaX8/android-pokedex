package com.rafael.mardom.features.pokedex.data.remote.api

import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.features.pokedex.domain.Pokemon
import com.rafael.mardom.features.pokedex.domain.PokemonSprite
import com.rafael.mardom.features.pokedex.domain.PokemonStats

fun PokemonApiModel.toDomain(description: PokemonEntryApiModel) = Pokemon(
    id = this.id,
    name = this.name,
    description = description.toDomain(),
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
    frontDefault = this.frontDefault,
    frontShiny = this.frontShiny,
    backDefault = this.backDefault,
    backShiny = this.backShiny,
)