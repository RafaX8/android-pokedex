package com.rafael.mardom.app.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rafael.mardom.features.pokedex.data.local.db.PokemonStatsEntity

class DbConverters {
    @TypeConverter
    fun listStatsToJson(value: List<PokemonStatsEntity>): String = Gson().toJson(value)

    @TypeConverter
    fun listStringToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String): List<String> =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun jsonToStatsList(value: String): List<PokemonStatsEntity> =
        Gson().fromJson(value, Array<PokemonStatsEntity>::class.java).toList()
}