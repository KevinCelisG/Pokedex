package com.example.pokdex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.data.model.PokemonResponse
import com.example.pokdex.domain.GetPokemonsResponseUseCase
import com.example.pokdex.domain.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonsResponseUseCase: GetPokemonsResponseUseCase
) :
    ViewModel() {

    val listPokemonsResponse = MutableLiveData<PokemonResponse>()
    val listPokemons = MutableLiveData<List<Pokemon>>()

    fun getPokemonsResponse(query: String) {
        viewModelScope.launch {
            val response = getPokemonsResponseUseCase.invoke(query)

            if (response != null) {
                listPokemonsResponse.postValue(response!!)
                getPokemons(response)
            }
        }
    }

    private fun getPokemons(pokemonResponse: PokemonResponse) {
        viewModelScope.launch {
            val pokemons = getPokemonsUseCase.invoke(pokemonResponse?.results)

            if (pokemons != null) {
                listPokemons.postValue(pokemons!!)
            }
        }
    }

}