package com.example.pokdex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokdex.data.database.dao.PokemonDao
import com.example.pokdex.data.database.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    /**
     * Get a instance of pokemonDao
     * @return PokemonDao = Instance of pokemonDao
     */
    abstract fun getPokemonDao(): PokemonDao
}