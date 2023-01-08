package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY id")
    suspend fun getPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listPokemonsEntities: List<PokemonEntity>)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemons()
}