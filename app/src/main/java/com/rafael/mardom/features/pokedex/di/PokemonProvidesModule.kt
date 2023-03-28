package com.rafael.mardom.features.pokedex.di

import com.rafael.mardom.features.pokedex.data.remote.api.ApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonProvidesModule {

    @Singleton
    @Provides
    fun providesPokemonApiEndPoints(retrofit: Retrofit) : ApiEndPoints =
        retrofit.create(ApiEndPoints::class.java)

}