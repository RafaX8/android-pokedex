package com.rafael.mardom.features.pokedex.data

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.features.pokedex.domain.Pokemon
import com.rafael.mardom.features.pokedex.domain.PokemonRepository
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
    // private val localDataSource: PokemonLocalDataSource, // Será añadido en el futuro
    // private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getAll(): Either<ErrorApp, List<Pokemon>> {
        TODO("Not yet implemented")
    }
}