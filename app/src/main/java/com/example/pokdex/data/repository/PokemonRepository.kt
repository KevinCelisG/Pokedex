package com.example.pokdex.data.repository

import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.data.network.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonService: PokemonService) {

    suspend fun getPokemonsResponse(query: String): PokemonResponse? {
        return pokemonService.getPokemonsResponse(query)
    }

    suspend fun getPokemons(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<Pokemon>? {
        return pokemonService.getPokemons(listPokemonsResponse)
    }

}