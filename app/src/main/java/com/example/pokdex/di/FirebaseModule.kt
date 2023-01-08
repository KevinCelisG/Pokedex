package com.example.pokdex.di

import com.example.pokdex.data.network.PokemonFirebaseClient
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    /**
     * Provide a unique instance of Firestore at the project
     * @return FirebaseFirestore.getInstance() = Instance of Firestore
     */
    @Singleton
    @Provides
    fun provideFirebase(): FirebaseFirestore = FirebaseFirestore.getInstance()

    /**
     * Provide a unique instance of PokemonFirebaseClient at the project
     * @param firestore = Instance of Firestore
     * @return PokemonFirebaseClient = Instance of PokemonFirebaseClient
     */
    @Singleton
    @Provides
    fun providePokemonService(firestore: FirebaseFirestore): PokemonFirebaseClient =
        PokemonFirebaseClient(firestore.collection("responses").document())
}