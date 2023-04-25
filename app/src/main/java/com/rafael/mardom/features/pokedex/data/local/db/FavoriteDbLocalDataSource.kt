package com.rafael.mardom.features.pokedex.data.local.db

import com.rafael.mardom.features.pokedex.data.local.FavoriteLocalDataSource
import com.rafael.mardom.features.pokedex.domain.Favorite
import javax.inject.Inject

class FavoriteDbLocalDataSource @Inject constructor(private var dao: FavoriteDao) :
    FavoriteLocalDataSource {

    override fun getById(id: Int): Favorite? {
        return dao.findById(id)?.toDomain()
    }

    override fun save(favorite: Favorite) {
        dao.save(favorite.toEntity())
    }

    override fun deleteById(id: Int) {
        dao.deleteById(id)
    }
}