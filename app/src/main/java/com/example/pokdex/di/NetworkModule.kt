package com.example.pokdex.di

import com.example.pokdex.core.Constants
import com.example.pokdex.data.network.PokemonApiClient
import com.example.pokdex.ui.viewmodel.PokemonViewModel
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

    // Provide just one instance of retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(Constants.ROOT_PATH)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providePokemonApiClient(retrofit: Retrofit): PokemonApiClient =
        retrofit.create(PokemonApiClient::class.java)

}