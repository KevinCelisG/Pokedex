package com.example.pokdex.ui.view

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokdex.R
import com.example.pokdex.core.Constants
import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.databinding.ActivityPokemonBinding
import com.example.pokdex.domain.model.Pokemon
import com.squareup.picasso.Picasso
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@Suppress("DEPRECATION")
class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding
    private lateinit var pokemon: Pokemon

    // Initialize the binding and get and set the data about a specific Pokemon
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

    /**
     *This method set a specific data about a Pokemon. The data is:
     *Image, experience, height, weight, id and name
     */
    private fun setData() {
        val listImages = mutableListOf<CarouselItem>()
        listImages.add(CarouselItem(pokemon.front_default))
        listImages.add(CarouselItem(pokemon.front_shiny))
        listImages.add(CarouselItem(pokemon.back_default))
        listImages.add(CarouselItem(pokemon.back_shiny))
        binding.carouselImagePokemon.setData(listImages)
        binding.textViewBaseExperience.text =
            getString(R.string.experience_text) + pokemon.experience.toString()
        binding.textViewHeight.text =
            getString(R.string.height_text) + pokemon.height.toString() + getString(R.string.meters_text)
        binding.textViewWeight.text =
            getString(R.string.weight_text) + pokemon.weight.toString() + getString(R.string.kilograms_text)
        binding.textViewId.text = getString(R.string.pillow_text) + pokemon.id.toString()
        binding.textViewName.text = pokemon.name
    }

}