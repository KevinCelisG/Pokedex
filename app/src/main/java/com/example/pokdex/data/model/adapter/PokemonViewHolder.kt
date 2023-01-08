package com.example.pokdex.data.model.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokdex.databinding.ItemPokemonBinding
import com.example.pokdex.domain.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    /**
     * Render a data of pokemon in a Layout
     * @param pokemon = The pokemon with his data
     */
    @SuppressLint("SetTextI18n")
    fun render(pokemon: Pokemon) {
        Picasso.get().load(pokemon.front_default).into(binding.imageViewImagePokemon)
        binding.textViewName.text = pokemon.name
        binding.textViewId.text = "#${pokemon.id}"
    }
}