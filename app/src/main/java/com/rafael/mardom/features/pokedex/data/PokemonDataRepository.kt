package com.rafael.mardom.features.pokedex.data

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.app.domain.functional.left
import com.rafael.mardom.app.domain.functional.right
import com.rafael.mardom.features.pokedex.data.local.PokemonLocalDataSource
import com.rafael.mardom.features.pokedex.data.remote.PokemonRemoteDataSource
import com.rafael.mardom.features.pokedex.domain.Pokemon
import com.rafael.mardom.features.pokedex.domain.PokemonRepository
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getAll(): Either<ErrorApp, List<Pokemon>> {
        val pokedexLocal = localDataSource.getAll()

         try {
            if (pokedexLocal.isLeft() || pokedexLocal.get().isEmpty()) {
                remoteDataSource.getAll().fold(
                    {
                        return it.left()
                    },
                    {
                        localDataSource.save(it)
                        return it.right()
                    }
                )
            } else {
                return pokedexLocal.get().right()
            }
        } catch (e: java.lang.Exception) {
            return ErrorApp.DataError.left()
        }
    }

    override suspend fun getById(id: Int): Either<ErrorApp, Pokemon?> = localDataSource.getById(id)
}