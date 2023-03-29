package com.rafael.mardom.features.pokedex.data.local.db

import com.rafael.mardom.features.pokedex.domain.Pokemon
import com.rafael.mardom.features.pokedex.domain.PokemonSprite
import com.rafael.mardom.features.pokedex.domain.PokemonStats

fun PokemonEntity.toDomain() = Pokemon(
    id = this.id,
    name = this.name,
    description = this.description,
    height = this.height,
    weight = this.weight,
    types = this.types,
    stats = this.stats.map {
        it.toDomain()
    },
    sprites = this.sprites.toDomain()
)

fun PokemonStatsEntity.toDomain() = PokemonStats(
    name = this.name,
    base = this.base
)

fun PokemonSpriteEntity.toDomain() = PokemonSprite(
    front_default = this.front_default,
    front_shiny = this.front_shiny,
    back_default = this.back_default,
    back_shiny = this.back_shiny,
)

fun Pokemon.toEntity() = PokemonEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    height = this.height,
    weight = this.weight,
    types = this.types,
    stats = this.stats.map {
        it.toEntity()
    },
    sprites = this.sprites.toEntity()
)

fun PokemonStats.toEntity() = PokemonStatsEntity(
    name = this.name,
    base = this.base
)

fun PokemonSprite.toEntity() = PokemonSpriteEntity(
    front_default = this.front_default,
    front_shiny = this.front_shiny,
    back_default = this.back_default,
    back_shiny = this.back_shiny,
)