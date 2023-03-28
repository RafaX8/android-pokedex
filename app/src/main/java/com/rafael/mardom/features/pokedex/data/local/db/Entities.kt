package com.rafael.mardom.features.pokedex.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_POKEMON = "pokemon"
const val PK_POKEMON = "pokemon_id"
const val NAME_POKEMON = "pokemon_name"

const val TABLE_STATS = "pokemon_stats"
const val PK_STATS = "pokemon_stats_id"

const val TABLE_SPRITE = "pokemon_sprite"
const val PK_SPRITE = "pokemon_sprite_id"

@Entity(tableName = TABLE_POKEMON)
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = PK_POKEMON) val id: Int,
    @ColumnInfo(name = NAME_POKEMON) val name: String,
    val description: String,
    val height: Double,
    val weight: Double,
    val types: List<String>,
    val stats: List<PokemonStatsEntity>,
    @Embedded val sprites: PokemonSpriteEntity,
)

@Entity(tableName = TABLE_STATS)
data class PokemonStatsEntity(
    @PrimaryKey
    @ColumnInfo(name = PK_STATS)
    val name: String,
    val base: Int
)

@Entity(tableName = TABLE_SPRITE)
data class PokemonSpriteEntity(
    @PrimaryKey
    @ColumnInfo(name = PK_SPRITE) val front_default: String,
    val front_shiny: String,
    val back_default: String,
    val back_shiny: String,
)