package com.rafael.mardom.features.pokedex.data

import com.rafael.mardom.features.pokedex.data.local.FavoriteLocalDataSource
import com.rafael.mardom.features.pokedex.domain.Favorite
import com.rafael.mardom.features.pokedex.domain.FavoriteRepository
import javax.inject.Inject

class FavoriteDataRepository @Inject constructor(
    private val localDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override fun getById(id: Int): Favorite? {
        return localDataSource.getById(id)
    }

    override fun save(favorite: Favorite) {
        localDataSource.save(favorite)
    }

    override fun deleteById(id: Int) {
        localDataSource.deleteById(id)
    }
}