package com.rafael.mardom.features.pokedex.data.remote.api

import com.rafael.mardom.app.data.apiCall
import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.app.domain.functional.left
import com.rafael.mardom.app.domain.functional.right
import com.rafael.mardom.features.pokedex.data.remote.PokemonRemoteDataSource
import com.rafael.mardom.features.pokedex.domain.Pokemon
import javax.inject.Inject

class PokemonApiRemoteDataSource @Inject constructor(
    private val apiClient: ApiEndPoints
) : PokemonRemoteDataSource {

    private val pokemonNumber: Int = 151

    override suspend fun getAll(): Either<ErrorApp, List<Pokemon>> {
        val pokedex = mutableListOf<Pokemon>()
        return try {
            for (num in 1..pokemonNumber) {
                val pokemon = getById(num).get()
                pokedex.add(pokemon)
            }
            return pokedex.right()
        } catch (e: java.lang.Exception) {
            ErrorApp.DataError.left()
        }
    }

    private suspend fun getById(id: Int): Either<ErrorApp, Pokemon> {
        return try {
            apiCall {
                apiClient.getPokemonById(id)
            }.fold(
                {
                    it.left()
                },
                {
                    val pokemonDescription = getPokemonDescription(id).get()
                    it.toDomain(pokemonDescription).right()
                })
        } catch (e: java.lang.Exception) {
            return ErrorApp.DataError.left()
        }
    }

    private suspend fun getPokemonDescription(id: Int): Either<ErrorApp, PokemonEntryApiModel> {
        return try {
            apiCall {
                apiClient.getPokemonEntryById(id)
            }.get().right()
        } catch (e: java.lang.Exception) {
            return ErrorApp.DataError.left()
        }
    }
}