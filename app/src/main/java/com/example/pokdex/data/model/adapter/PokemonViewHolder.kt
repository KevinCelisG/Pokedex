package com.example.pokdex.data.model.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemon: Pokemon) {
        Picasso.get().load(pokemon.sprite.front_default).into(binding.imageViewImagePokemon)
        binding.textViewName.text = pokemon.name
    }
}