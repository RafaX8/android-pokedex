package com.rafael.mardom.features.pokedex.data.remote.api

import com.google.gson.annotations.SerializedName

data class PokemonApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("species") val description: List<PokemonEntryApiModel>,
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double,
    @SerializedName("types") val types: List<PokemonTypesApiModel>,
    @SerializedName("stats") val stats: List<PokemonStatsApiModel>,
    @SerializedName("sprites") val sprites: PokemonSpriteApiModel,
)

data class PokemonEntryApiModel(
    @SerializedName("flavor_text_entries") val entry: List<EntryApiModel>
)

data class EntryApiModel(
    @SerializedName("flavor_text") val description: String
)

data class PokemonTypesApiModel(
    @SerializedName("type") val type: TypeApiModel
)

data class TypeApiModel(
    @SerializedName("name") val name: String
)

data class PokemonStatsApiModel(
    @SerializedName("stat") val stat: StatApiModel,
    @SerializedName("base_stat") val base: Int
)

data class StatApiModel(
    @SerializedName("name") val name: String
)

data class PokemonSpriteApiModel(
    @SerializedName("front_default") val front_default: String,
    @SerializedName("front_shiny") val front_shiny: String,
    @SerializedName("back_default") val back_default: String,
    @SerializedName("back_shiny") val back_shiny: String,
)