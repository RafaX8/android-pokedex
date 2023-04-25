package com.rafael.mardom.features.pokedex.data.local

import com.rafael.mardom.features.pokedex.domain.Favorite

interface FavoriteLocalDataSource {
    fun getById(id: Int): Favorite?
    fun save(favorite: Favorite)
    fun deleteById(id: Int)
}