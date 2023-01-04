package com.example.pokdex.domain

import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.data.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsResponseUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    suspend fun invoke(query: String): PokemonResponse? =
        pokemonRepository.getPokemonsResponse(query)

}