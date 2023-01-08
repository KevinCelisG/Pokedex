package com.example.pokdex.domain

import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.data.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsResponseUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    /**
     * Get a pokemonsResponse using a pokemonRepository
     * @param query = Query with a data of a endpoints of the API that have data of the pokemons
     * @return pokemonResponse = Object with a data of pokemons
     */
    suspend fun invoke(query: String): PokemonResponse? =
        pokemonRepository.getPokemonsResponse(query)
}
