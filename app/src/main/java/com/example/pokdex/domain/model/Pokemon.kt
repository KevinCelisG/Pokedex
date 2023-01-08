package com.example.pokdex.domain.model

import com.example.pokdex.data.database.entities.PokemonEntity

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val experience: Int,
    val front_default: String,
    val front_shiny: String,
    val back_default: String,
    val back_shiny: String
) : java.io.Serializable

fun PokemonEntity.toDomain() = Pokemon(
    id,
    name,
    weight,
    height,
    experience,
    front_default,
    front_shiny,
    back_default,
    back_shiny
)