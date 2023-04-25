package com.rafael.mardom.features.pokedex.di

import com.rafael.mardom.features.pokedex.data.local.db.FavoriteDao
import com.rafael.mardom.app.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesProvidesModule {
    @Singleton
    @Provides
    fun provideFavoriteDao(appDataBase: AppDataBase): FavoriteDao {
        return appDataBase.favoriteDao()
    }
}