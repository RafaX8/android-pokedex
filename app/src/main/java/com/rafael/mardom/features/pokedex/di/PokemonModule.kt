package com.rafael.mardom.features.pokedex.di

import com.rafael.mardom.features.pokedex.data.PokemonDataRepository
import com.rafael.mardom.features.pokedex.data.local.PokemonLocalDataSource
import com.rafael.mardom.features.pokedex.data.local.db.PokemonDbLocalDataSource
import com.rafael.mardom.features.pokedex.data.remote.PokemonRemoteDataSource
import com.rafael.mardom.features.pokedex.data.remote.api.PokemonApiRemoteDataSource
import com.rafael.mardom.features.pokedex.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonModule {

    @Binds
    abstract fun bindPokemonRepository(repository: PokemonDataRepository): PokemonRepository

    @Binds
    abstract fun bindPokemonRemoteApiDataSource(remoteSource: PokemonApiRemoteDataSource): PokemonRemoteDataSource

    @Binds
    abstract fun bindPokemonDbLocalDataSource(localSource: PokemonDbLocalDataSource): PokemonLocalDataSource
}