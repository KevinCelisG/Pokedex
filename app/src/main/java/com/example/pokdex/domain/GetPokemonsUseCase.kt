package com.example.pokdex.domain

import com.example.pokdex.data.database.entities.toDatabase
import com.example.pokdex.data.repository.PokemonRepository
import com.example.pokdex.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    /**
     * Get a pokemons using a pokemonRepository
     * @param listPokemonsResponse = ListPokemonsResponse with a data of a endpoints of the API that have data of the pokemons
     * @return listPokemons = List with a data of pokemons
     */
    suspend fun invoke(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<Pokemon>? {
        return if (listPokemonsResponse != null) {
            val listPokemons = pokemonRepository.getPokemons(listPokemonsResponse)
            pokemonRepository.insertPokemons(listPokemons!!.map { it.toDatabase() })
            listPokemons
        } else {
            pokemonRepository.getPokemonsFromDatabase()
        }
    }

    /**
     * Clear all the pokemons in the database calling to pokemonRepository
     */
    suspend fun clearAllPokemons() {
        pokemonRepository.clearPokemons()
    }
}