package com.rafael.mardom.features.pokedex.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM $TABLE_NAME_FAVORITE WHERE $PK_NAME_FAVORITE = :id")
    fun findById(id: Int): FavoriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM $TABLE_NAME_FAVORITE WHERE $PK_NAME_FAVORITE = :id")
    fun deleteById(id: Int)
}