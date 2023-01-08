package com.example.pokdex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.domain.GetPokemonsResponseUseCase
import com.example.pokdex.domain.GetPokemonsUseCase
import com.example.pokdex.domain.SavePokemonsResponse
import com.example.pokdex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonsResponseUseCase: GetPokemonsResponseUseCase,
    private val savePokemonsResponse: SavePokemonsResponse
) :
    ViewModel() {

    private val listPokemonsGeneral = ArrayList<Pokemon>()
    val listPokemonsResponse = MutableLiveData<PokemonResponse?>()
    val listPokemons = MutableLiveData<ArrayList<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    /**
     *Put a pokemon response into a listPokemonsResponse
     *@param query = query with a specific pokemons based in the pagination of the API
     */
    fun getPokemonsResponse(query: String) {
        viewModelScope.launch {
            var response: PokemonResponse? = null
            isLoading.postValue(true)
            response = getPokemonsResponseUseCase.invoke(query)
            listPokemonsResponse.postValue(response)
        }
    }

    fun clearAllPokemons() {
        viewModelScope.launch {
            println("Se borraron los pokemones weon")
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
                listPokemonsGeneral.addAll(pokemons)
                listPokemons.postValue(listPokemonsGeneral)
                isLoading.postValue(false)
            }
        }
    }

    /**
     * This method call a use case savePokemonsResponse to save the data of responses in a file txt
     */
    fun saveResponses() {
        viewModelScope.launch {
            savePokemonsResponse.invoke()
        }
    }

}