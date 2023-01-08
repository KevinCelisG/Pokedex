package com.example.pokdex.data.network

import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApiClient {

    /**
     * Get a list of pokemonsResponse based in the query
     * @param query = Query that have a init pagination of the API
     * @return Response<PokemonResponse> = Response with a PokemonResponse that have data about the endpoints to another pokemons in the API
     */
    @GET
    suspend fun getPokemonsResponse(@Url query: String): Response<PokemonResponse>?

    /**
     * Get a list of pokemons based in the query
     * @param query = Query that have a init pagination of the API
     * @return Response<PokemonModel> = Response with a PokemonModel that have data about the pokemons
     */
    @GET
    suspend fun getPokemon(@Url query: String): Response<PokemonModel>?
}