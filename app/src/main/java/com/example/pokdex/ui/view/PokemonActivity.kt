package com.example.pokdex.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokdex.R
import com.example.pokdex.core.Constants
import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.databinding.ActivityPokemonBinding
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding
    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemon = intent.getSerializableExtra(Constants.POKEMON_KEY) as Pokemon

        binding.buttonImageGoBack.setOnClickListener {
            finish()
        }

        setData()
    }

    /*
    * This method set a specific data about a Pokemon. The data is:
    * Image, experience, height, weight, id and name
    * */
    private fun setData() {
        Picasso.get().load(pokemon.sprite.front_default).into(binding.imageViewImagePokemon)
        binding.textViewBaseExperience.text = pokemon.experience.toString()
        binding.textViewHeight.text = pokemon.height.toString()
        binding.textViewWeight.text = pokemon.weight.toString()
        binding.textViewId.text = pokemon.id.toString()
        binding.textViewName.text = pokemon.name
    }

}