package com.example.pokdex.data.repository

import com.example.pokdex.core.Utils
import com.example.pokdex.data.database.dao.PokemonDao
import com.example.pokdex.data.database.entities.PokemonEntity
import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.data.network.PokemonService
import com.example.pokdex.domain.model.Pokemon
import com.example.pokdex.domain.model.toDomain
import javax.inject.Inject
import kotlin.collections.ArrayList

class PokemonRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {

    suspend fun getPokemonsResponse(query: String): PokemonResponse? {
        return pokemonService.getPokemonsResponse(query)
    }

    suspend fun getPokemons(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<Pokemon>? {
        val listPokemons = ArrayList<Pokemon>()
        val listPokemonsModel = pokemonService.getPokemons(listPokemonsResponse)
        if (!listPokemonsModel.isNullOrEmpty()) {
            for (pokemon in listPokemonsModel) {
                listPokemons.add(Utils.converterPokemon(pokemon))
            }
            return listPokemons
        }
        return null
    }

    suspend fun getPokemonsFromDatabase(): List<Pokemon>? {
        val listPokemons = pokemonDao.getPokemons()
        return listPokemons.map { it.toDomain() }
    }

    suspend fun insertPokemons(listPokemonsEntities: List<PokemonEntity>) {
        pokemonDao.insertAll(listPokemonsEntities)
    }

    suspend fun clearPokemons() {
        pokemonDao.deleteAllPokemons()
    }

    /**
     * Call to function in pokemonService to save the data
     */
    fun saveResponses() {
        pokemonService.savePokemons()
    }
}