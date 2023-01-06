package com.example.pokdex.di

import com.example.pokdex.data.network.PokemonFirebaseClient
import com.example.pokdex.data.network.PokemonService
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providePokemonService(firestore: FirebaseFirestore): PokemonFirebaseClient {
        return PokemonFirebaseClient(firestore.collection("responses").document())
    }
}