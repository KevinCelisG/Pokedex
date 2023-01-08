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

    /**
     * Get a pokemonsResponse using a pokemonService
     * @param query = Query with a data of a endpoints of the API that have data of the pokemons
     * @return pokemonResponse = Object with a data of pokemons
     */
    suspend fun getPokemonsResponse(query: String): PokemonResponse? {
        return pokemonService.getPokemonsResponse(query)
    }

    /**
     * Get a pokemons using a pokemonService
     * @param listPokemonsResponse = ListPokemonsResponse with a data of a endpoints of the API that have data of the pokemons
     * @return listPokemons = List with a data of pokemons
     */
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

    /**
     * Call to function in pokemonDao to get all pokemons in the database
     * @return listPokemons = List of Pokemons
     */
    suspend fun getPokemonsFromDatabase(): List<Pokemon> {
        val listPokemons = pokemonDao.getPokemons()
        return listPokemons.map { it.toDomain() }
    }

    /**
     * Call to function in pokemonDao to insert all pokemons in the database
     * @param listPokemonsEntities = List of PokemonEntity
     */
    suspend fun insertPokemons(listPokemonsEntities: List<PokemonEntity>) {
        pokemonDao.insertAll(listPokemonsEntities)
    }

    /**
     * Call to function in pokemonDao to delete all pokemons in the database
     */
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