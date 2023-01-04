package com.example.pokdex.data.network

import com.example.pokdex.core.Constants
import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.data.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonService @Inject constructor(private val pokemonApiClient: PokemonApiClient) {

    suspend fun getPokemonsResponse(query: String): PokemonResponse? {
        return withContext(Dispatchers.IO) {
            val response = pokemonApiClient.getPokemonsResponse(query)

            // Print the response
            println("RESPONSE")
            println(response)

            response.body()
        }
    }

    suspend fun getPokemons(listPokemonsResponse: List<com.example.pokdex.data.model.Result>?): List<Pokemon>? {
        listPokemonsResponse.let {
            return withContext(Dispatchers.IO) {
                var listPokemons = ArrayList<Pokemon>()

                for (i in listPokemonsResponse!!) {
                    var pokemon =
                        pokemonApiClient.getPokemon(i.url.replace(Constants.ROOT_PATH, ""))
                    listPokemons.add(pokemon.body()!!)
                    println("Url: ${i.url.replace(Constants.ROOT_PATH, "")}")
                }

                println("Lista de pokemones \n $listPokemons")

                listPokemons
            }
        }.run {
            return emptyList()
        }
    }

}
