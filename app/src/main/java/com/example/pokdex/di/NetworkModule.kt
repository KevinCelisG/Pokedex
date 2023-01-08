package com.example.pokdex.di

import com.example.pokdex.core.Constants
import com.example.pokdex.data.network.PokemonApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provide a unique instance of Retrofit at the project
     * @return Retrofit = Instance of Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(Constants.ROOT_PATH)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Provide a unique instance of PokemonApiClient at the project
     * @param retrofit = Instance of Retrofit
     * @return PokemonApiClient = Instance of PokemonApiClient
     */
    @Singleton
    @Provides
    fun providePokemonApiClient(retrofit: Retrofit): PokemonApiClient =
        retrofit.create(PokemonApiClient::class.java)

}