package com.example.pokdex.di

import android.content.Context
import androidx.room.Room
import com.example.pokdex.core.Constants
import com.example.pokdex.data.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PokemonDatabase::class.java, Constants.DATA_BASE_NAME).build()

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDatabase) = database.getPokemonDao()
}