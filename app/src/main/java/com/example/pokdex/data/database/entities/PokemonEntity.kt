package com.example.pokdex.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "base_experience") val experience: Int,
    @ColumnInfo(name = "front_default") val front_default: String,
    @ColumnInfo(name = "front_shiny") val front_shiny: String,
    @ColumnInfo(name = "back_default") val back_default: String,
    @ColumnInfo(name = "back_shiny") val back_shiny: String
)

fun Pokemon.toDatabase() = PokemonEntity(
    id = id,
    name = name,
    weight = weight,
    height = height,
    experience = experience,
    front_default = front_default,
    front_shiny = front_shiny,
    back_default = back_default,
    back_shiny = back_shiny
)