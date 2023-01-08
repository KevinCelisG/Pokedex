package com.example.pokdex.domain

import com.example.pokdex.data.repository.PokemonRepository
import javax.inject.Inject

class SavePokemonsResponseUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    /**
     * Save the data of responses using to pokemonRepository
     */
    fun invoke() {
        pokemonRepository.saveResponses()
    }
}