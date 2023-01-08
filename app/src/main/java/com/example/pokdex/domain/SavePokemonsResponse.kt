package com.example.pokdex.domain

import com.example.pokdex.data.repository.PokemonRepository
import javax.inject.Inject

class SavePokemonsResponse @Inject constructor(private val pokemonRepository: PokemonRepository) {

    /**
     * This method invoke a use case savePokemonResponses that save the data of responses using to pokemonRepository
     */
    fun invoke() {
        pokemonRepository.saveResponses()
    }
}