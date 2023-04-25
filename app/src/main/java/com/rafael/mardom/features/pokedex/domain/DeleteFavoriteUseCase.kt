package com.rafael.mardom.features.pokedex.domain

import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(favoriteId: Int) {
        favoriteRepository.deleteById(favoriteId)
    }
}