package com.example.pokdex.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("base_experience") val experience: Int,
    @SerializedName("sprites") val sprite: Sprite
) : Serializable

data class Sprite(
    @SerializedName("front_default") val front_default: String,
    @SerializedName("front_shiny") val front_shiny: String,
    @SerializedName("back_default") val back_default: String,
    @SerializedName("back_shiny") val back_shiny: String
) : Serializable