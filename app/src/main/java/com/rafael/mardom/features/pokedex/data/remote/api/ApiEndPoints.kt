package com.rafael.mardom.features.pokedex.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoints {

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Response<PokemonApiModel>
    @GET("pokemon-species/{id}")
    suspend fun getPokemonEntryById(@Path("id") id: Int): Response<PokemonEntryApiModel>

}