package com.example.pokdex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.domain.GetPokemonsResponseUseCase
import com.example.pokdex.domain.GetPokemonsUseCase
import com.example.pokdex.domain.SavePokemonsResponseUseCase
import com.example.pokdex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonsResponseUseCase: GetPokemonsResponseUseCase,
    private val savePokemonsResponseUseCase: SavePokemonsResponseUseCase
) :
    ViewModel() {

    val listPokemonsResponse = MutableLiveData<PokemonResponse?>()
    val listPokemons = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    /**
     * Put a pokemon response into a listPokemonsResponse
     * @param query = query with a specific pokemons based in the pagination of the API
     */
    fun getPokemonsResponse(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response: PokemonResponse? = getPokemonsResponseUseCase.invoke(query)
            listPokemonsResponse.postValue(response)
        }
    }

    /**
     * Clear all the pokemons in the database calling to getPokemonsUseCase
     */
    fun clearAllPokemons() {
        viewModelScope.launch {
            getPokemonsUseCase.clearAllPokemons()
        }
    }

    /**
     * Put a pokemon list into a listPokemons
     * @param pokemonResponse = pokemonResponse that have specifics pokemons based in the pagination of the API
     */
    fun getPokemons(pokemonResponse: PokemonResponse?) {
        viewModelScope.launch {
            val pokemons = getPokemonsUseCase.invoke(pokemonResponse?.results)

            if (pokemons != null) {
                listPokemons.postValue(pokemons!!)
                isLoading.postValue(false)
            }
        }
    }

    /**
     * Call a use case savePokemonsResponse to save the data of responses in a file txt
     */
    fun saveResponses() {
        viewModelScope.launch {
            savePokemonsResponseUseCase.invoke()
        }
    }

}