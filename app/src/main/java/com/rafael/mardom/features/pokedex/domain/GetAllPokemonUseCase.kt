package com.rafael.mardom.features.pokedex.domain

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, List<PokemonItem>> {
        return pokemonRepository.getAll().map {
            it.map { pokemon ->
                PokemonItem(
                    id = pokemon.id,
                    name= pokemon.name,
                    types= pokemon.types,
                    sprite= pokemon.sprites.frontDefault
                )
            }
        }
    }

    data class PokemonItem(
        val id: Int,
        val name: String,
        val types: List<String>,
        val sprite: String,
    )
}