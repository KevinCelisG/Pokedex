package com.example.pokdex.data.network

import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApiClient {

    @GET
    suspend fun getPokemonsResponse(@Url query: String): Response<PokemonResponse>

    @GET
    suspend fun getPokemon(@Url query: String): Response<Pokemon>
}