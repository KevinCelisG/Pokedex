package com.example.pokdex.domain

import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.data.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    suspend fun invoke(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<Pokemon>? =
        pokemonRepository.getPokemons(listPokemonsResponse)
}