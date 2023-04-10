package com.rafael.mardom.features.pokedex.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM $TABLE_POKEMON")
    fun findAll(): List<PokemonEntity>

    @Query("SELECT * FROM $TABLE_POKEMON WHERE $PK_POKEMON = :id")
    fun findById(id: Int): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM $TABLE_POKEMON")
    fun deleteAll()
}