package com.rafael.mardom.features.pokedex.domain

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import javax.inject.Inject

class FilterFavoritesFeedUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val favoriteRepository: FavoriteRepository,
) {

    suspend operator fun invoke(): Either<ErrorApp, List<GetAllPokemonUseCase.PokemonItem>> {
        return pokemonRepository.getAll().map {
            filterPokemonByFavorites(it)
        }
    }

    private fun filterPokemonByFavorites(pokedex: List<Pokemon>): List<GetAllPokemonUseCase.PokemonItem> {
        return pokedex.filter { pokemon ->
            favoriteRepository.getById(pokemon.id) != null
        }.map { pokemon ->
            GetAllPokemonUseCase.PokemonItem(
                id = pokemon.id,
                name = pokemon.name,
                types = pokemon.types,
                sprite = pokemon.sprites.frontDefault,
                isFavorite = true
            )
        }
    }
}