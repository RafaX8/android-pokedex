package com.rafael.mardom.features.pokedex.di

import com.rafael.mardom.features.pokedex.data.FavoriteDataRepository
import com.rafael.mardom.features.pokedex.data.local.FavoriteLocalDataSource
import com.rafael.mardom.features.pokedex.data.local.db.FavoriteDbLocalDataSource
import com.rafael.mardom.features.pokedex.domain.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoriteModule {
    @Binds
    abstract fun bindFavoriteRepository(repository: FavoriteDataRepository): FavoriteRepository

    @Binds
    abstract fun bindFavoriteDbLocalDataSource(localSource: FavoriteDbLocalDataSource): FavoriteLocalDataSource
}