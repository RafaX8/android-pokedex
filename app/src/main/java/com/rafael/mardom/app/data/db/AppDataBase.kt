package com.rafael.mardom.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafael.mardom.BuildConfig
import com.rafael.mardom.features.pokedex.data.local.db.*

@Database(
    entities = [
        PokemonEntity::class,
        PokemonStatsEntity::class,
        PokemonSpriteEntity::class,
        FavoriteEntity::class,
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(DbConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun favoriteDao(): FavoriteDao
}