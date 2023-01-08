package com.example.pokdex.data.model.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokdex.R
import com.example.pokdex.core.Constants
import com.example.pokdex.domain.model.Pokemon
import com.example.pokdex.ui.view.PokemonActivity

class PokemonAdapter(private val listPokemonModels: List<Pokemon>) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    /**
     * Initialize a layoutInflater
     * @param parent = Parent with a context
     * @param viewType = Type of view
     * @return PokemonViewHolder = Holder with a methods to render a pokemon
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    /**
     * Manage a holder with a data of a pokemons in the listPokemonModels
     * @param holder = Holder with a methods to render a pokemon
     * @param position = Position specific of a pokemon in the listPokemonModels
     */
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonCurrent = listPokemonModels[position]
        holder.render(pokemonCurrent)
        holder.itemView.setOnClickListener {
            goToItemPokemon(
                pokemonCurrent, holder.itemView.context
            )
        }
    }

    /**
     *  Get a size of listPokemonModels
     *  @return int = Size of listPokemonModels
     */
    override fun getItemCount(): Int = listPokemonModels.size

    /**
     * Start a activity with a data ok a pokemon
     * @param pokemon = The pokemon with a his data
     * @param context = The context to drawn the data of pokemon
     */
    private fun goToItemPokemon(pokemon: Pokemon, context: Context) {
        val intent = Intent(context, PokemonActivity::class.java)
        intent.putExtra(Constants.POKEMON_KEY, pokemon)
        context.startActivity(intent)
    }

}