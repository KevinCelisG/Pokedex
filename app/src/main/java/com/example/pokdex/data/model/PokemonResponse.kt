package com.example.pokdex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("next") val next: String,
    @SerializedName("results") val results: List<com.example.pokdex.data.model.Result>
)

data class Result(@SerializedName("url") val url: String)