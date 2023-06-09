package com.rafael.mardom.features.pokedex.domain

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either

interface PokemonRepository {
    suspend fun getAll(): Either<ErrorApp, List<Pokemon>>
    suspend fun getById(id: Int): Either<ErrorApp, Pokemon?>
}

interface FavoriteRepository {
    fun getById(id: Int): Favorite?
    fun save(favorite: Favorite)
    fun deleteById(id: Int)
}