package com.example.pokdex.data.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokdex.R
import com.example.pokdex.data.model.Pokemon

class PokemonAdapter(private val listPokemons: List<Pokemon>) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonCurrent = listPokemons[position]
        holder.render(pokemonCurrent)
    }

    override fun getItemCount(): Int = listPokemons.size

}