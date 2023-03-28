package com.rafael.mardom.features.pokedex.data.local.db

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import com.rafael.mardom.app.domain.functional.left
import com.rafael.mardom.app.domain.functional.right
import com.rafael.mardom.features.pokedex.data.local.PokemonLocalDataSource
import com.rafael.mardom.features.pokedex.domain.Pokemon
import javax.inject.Inject

class PokemonDbLocalDataSource @Inject constructor(
    private var dao: PokemonDao
) : PokemonLocalDataSource {
    override fun getAll(): Either<ErrorApp, List<Pokemon>> {
        return try {

            val pokedex = dao.findAll()

            pokedex.map {
                it.toDomain()
            }.right()

        } catch (e: java.lang.Exception) {
            ErrorApp.DataError.left()
        }
    }

    override fun getById(id: Int): Either<ErrorApp, Pokemon?> {
        return try {

            val pokemon = dao.findById(id)

            pokemon?.toDomain().right()

        } catch (e: java.lang.Exception) {
            ErrorApp.DataError.left()
        }
    }

    override fun getByName(name: String): Either<ErrorApp, Pokemon?> {
        return try {

            val pokemon = dao.findByName(name)

            pokemon?.toDomain().right()

        } catch (e: java.lang.Exception) {
            ErrorApp.DataError.left()
        }
    }

    override fun save(pokedex: List<Pokemon>): Either<ErrorApp, Boolean> {
        return try {

            pokedex.forEach {
                dao.save(it.toEntity())
            }

            true.right()

        } catch (e: java.lang.Exception) {
            ErrorApp.DataError.left()
        }
    }

    override fun deleteAll() {
        dao.deleteAll()
    }
}