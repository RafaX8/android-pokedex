package com.rafael.mardom.features.pokedex.domain

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, List<Pokemon>> {
        return pokemonRepository.getAll()
    }
}