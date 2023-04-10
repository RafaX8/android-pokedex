package com.rafael.mardom.features.pokedex.data.local

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.features.pokedex.domain.Pokemon

interface PokemonLocalDataSource {
    fun getAll(): Either<ErrorApp, List<Pokemon>>
    fun getById(id: Int): Either<ErrorApp, Pokemon?>
    fun save(pokedex: List<Pokemon>): Either<ErrorApp, Boolean>
    fun deleteAll()
}