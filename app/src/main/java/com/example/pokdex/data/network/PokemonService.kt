package com.example.pokdex.data.network

import com.example.pokdex.core.Constants
import com.example.pokdex.core.Utils
import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.data.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val pokemonApiClient: PokemonApiClient,
    private val pokemonFirebaseClient: PokemonFirebaseClient
) {

    suspend fun getPokemonsResponse(query: String): PokemonResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = pokemonApiClient.getPokemonsResponse(query)
                if (response != null) {
                    pokemonFirebaseClient.postResponse(response)
                }
                response?.body()
            } catch (e: HttpException) {
                null
            } catch (e: IOException) {
                null
            }
        }
    }

    suspend fun getPokemons(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<PokemonModel>? {
        return withContext(Dispatchers.IO) {
            val listPokemonModels = ArrayList<PokemonModel>()
            for (i in listPokemonsResponse!!) {
                val pokemon =
                    pokemonApiClient.getPokemon(i.url!!.replace(Constants.ROOT_PATH, ""))
                listPokemonModels.add(pokemon?.body()!!)

            }
            listPokemonModels
        }
    }

    /**
     * Call a firebaseClient to save the data of responses
     */
    fun savePokemons() {
        pokemonFirebaseClient.saveResponses()
    }

}
