package com.example.pokdex.domain

import com.example.pokdex.data.database.entities.toDatabase
import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.data.repository.PokemonRepository
import com.example.pokdex.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    suspend fun invoke(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<Pokemon>? {
        return if (listPokemonsResponse != null) {
            val listPokemons = pokemonRepository.getPokemons(listPokemonsResponse)
            pokemonRepository.insertPokemons(listPokemons!!.map { it.toDatabase() });
            listPokemons
        } else {
            pokemonRepository.getPokemonsFromDatabase()
        }
    }

    suspend fun clearAllPokemons() {
        pokemonRepository.clearPokemons()
    }
}