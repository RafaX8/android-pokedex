package com.rafael.mardom.features.pokedex.domain

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import javax.inject.Inject

class GetPokemonByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(pokemonId: Int): Either<ErrorApp, PokemonDetail?> =
        pokemonRepository.getById(pokemonId).map {
            it?.let { pokemon ->
                PokemonDetail(
                    id = pokemon.id,
                    name = pokemon.name,
                    description = pokemon.description,
                    height = pokemon.height,
                    weight = pokemon.weight,
                    types = pokemon.types,
                    stats = pokemon.stats.map { stat ->
                        PokemonDetailStats(
                            name = stat.name,
                            base = stat.base
                        )
                    },
                    sprites = pokemon.sprites,
                    isFavorite = (favoriteRepository.getById(pokemon.id) != null)
                )
            }
        }

    data class PokemonDetail(
        val id: Int,
        val name: String,
        val description: String,
        val height: Double,
        val weight: Double,
        val types: List<String>,
        val stats: List<PokemonDetailStats>,
        val sprites: PokemonSprite,
        val isFavorite: Boolean
    )

    data class PokemonDetailStats(
        val name: String,
        val base: Int
    )
}