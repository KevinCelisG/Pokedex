package com.example.pokdex.data.model.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokdex.data.model.PokemonModel
import com.example.pokdex.databinding.ItemPokemonBinding
import com.example.pokdex.domain.model.Pokemon
import com.squareup.picasso.Picasso
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemon: Pokemon) {
        Picasso.get().load(pokemon.front_default).into(binding.imageViewImagePokemon)
        binding.textViewName.text = pokemon.name
        binding.textViewId.text = "#${pokemon.id}"
    }
}