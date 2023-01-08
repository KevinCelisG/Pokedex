package com.example.pokdex.core

import android.content.Intent
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.domain.model.Pokemon
import java.io.*
import java.time.Instant
import java.util.Calendar

/** This class is used in all of application, have a methods that help with a specific work in all application */
class Utils {
    companion object {

        /**
         * This method can converter a pokemonModel in a pokemon
         * @param pokemonModel = Pokemon with a structure based in API
         * @return pokemon = Pokemon with a structure based in objective of application
         */
        fun converterPokemon(pokemonModel: PokemonModel): Pokemon {
            return Pokemon(
                pokemonModel.id,
                pokemonModel.name,
                pokemonModel.weight,
                pokemonModel.height,
                pokemonModel.experience,
                pokemonModel.sprite.front_default,
                pokemonModel.sprite.front_shiny,
                pokemonModel.sprite.back_default,
                pokemonModel.sprite.back_shiny
            )
        }

        /**
         * This method add a list of Pokemon to another list of Pokemon
         * @param listPokemons = List base to add other list
         * @param listPokemonsToAdd = List to add at the base
         * @return listPokemons = List with a pokemons in listPokemons and pokemons in listPokemonsToAdd
         */
        fun addDataToArrayList(
            listPokemons: ArrayList<Pokemon>,
            listPokemonsToAdd: ArrayList<Pokemon>
        ): ArrayList<Pokemon> {
            println("List pokemons: \n $listPokemons")
            println("New list pokemons from internet: \n $listPokemonsToAdd")


            for (i in listPokemonsToAdd) {
                listPokemons.add(i)
            }
            return listPokemons
        }

        /**
         * This method can save a data in a file with extension txt
         * @param data = A String with a data to save
         * @return Boolean = Return if the operation was successful
         */
        fun save(data: String, nameFile: String): Boolean {
            return try {
                val documentsFolder = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS
                )
                val file = File(documentsFolder, "response_$nameFile.txt")
                file.writeText(data)
                true
            } catch (e: IOException) {
                false
            }
        }
    }
}