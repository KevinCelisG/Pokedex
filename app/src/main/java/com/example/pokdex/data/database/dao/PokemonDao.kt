package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.entities.PokemonEntity

@Dao
interface PokemonDao {

    /**
     * Get all pokemons in the database
     * @return list<PokemonEntity> = List of a PokemonEntity
     */
    @Query("SELECT * FROM pokemon ORDER BY id")
    suspend fun getPokemons(): List<PokemonEntity>

    /**
     * Insert a list of pokemons entity into the database
     * @param listPokemonsEntities = List a PokemonEntity to insert in the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listPokemonsEntities: List<PokemonEntity>)

    /**
     * Delete all pokemons in the database
     */
    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemons()
}