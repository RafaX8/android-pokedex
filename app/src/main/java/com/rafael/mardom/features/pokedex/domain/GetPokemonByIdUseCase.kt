package com.rafael.mardom.features.pokedex.domain

import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.app.domain.functional.Either
import javax.inject.Inject

class GetPokemonByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int): Either<ErrorApp, PokemonDetail?> =
        pokemonRepository.getById(pokemonId).map {
            it?.let { Pokemon ->
                PokemonDetail(
                    id = Pokemon.id,
                    name = Pokemon.name,
                    description = Pokemon.description,
                    height = Pokemon.height,
                    weight = Pokemon.weight,
                    types = Pokemon.types,
                    stats = Pokemon.stats.map { stat ->
                        PokemonDetailStats(
                            name = stat.name,
                            base = stat.base
                        )
                    },
                    sprites = Pokemon.sprites
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
    )

    data class PokemonDetailStats(
        val name: String,
        val base: Int
    )
}