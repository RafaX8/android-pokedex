package com.rafael.mardom.features.pokedex.data.remote

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.features.pokedex.domain.Pokemon

interface PokemonRemoteDataSource {
    suspend fun getAll(): Either<ErrorApp, List<Pokemon>>
}