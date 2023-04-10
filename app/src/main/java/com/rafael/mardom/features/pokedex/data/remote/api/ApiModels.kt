package com.rafael.mardom.features.pokedex.data.remote.api

import com.google.gson.annotations.SerializedName

data class PokemonApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
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
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_shiny") val frontShiny: String,
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("back_shiny") val backShiny: String,
)