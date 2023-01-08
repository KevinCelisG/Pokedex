package com.example.pokdex.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokdex.R
import com.example.pokdex.core.Constants
import com.example.pokdex.core.Utils
import com.example.pokdex.data.model.adapter.PokemonAdapter
import com.example.pokdex.databinding.ActivityMainBinding
import com.example.pokdex.domain.model.Pokemon
import com.example.pokdex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonVewModel: PokemonViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var listPokemons: ArrayList<Pokemon>
    private var isOnline: Boolean = false
    private var nextPage: String? = ""

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initRecyclerView()

        pokemonVewModel.listPokemonsResponse.observe(this, Observer {
            println("Funciono chamo :,D")
            println(it)
            nextPage = it?.next?.replace(Constants.ROOT_PATH, "").toString()
            pokemonVewModel.getPokemons(it)
            println("The next path no consume api is: $nextPage")
        })

        pokemonVewModel.listPokemons.observe(this, Observer {
            listPokemons = Utils.addDataToArrayList(listPokemons, it)
            pokemonAdapter.notifyDataSetChanged()
            println("Cantidad de pokemones total: ${listPokemons.size}")
            println("Cantidad de pokemones} llegando: ${it.size}")
        })

        pokemonVewModel.isLoading.observe(this, Observer {
            binding.progressCircular.isVisible = it
        })

        binding.buttonSaveResponses.setOnClickListener {
            saveResponses()
        }

        binding.nestedScrollViewList.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val lineDead = v.getChildAt(0).measuredHeight - v.measuredHeight
            if (scrollY == lineDead) {
                if (nextPage != null) {
                    pokemonVewModel.getPokemonsResponse(nextPage!!)
                } else {
                    Toast.makeText(this, "No hay más pokemons", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initData() {
        listPokemons = intent.getSerializableExtra(Constants.LIST_POKEMON_KEY) as ArrayList<Pokemon>
        nextPage = intent.getStringExtra(Constants.LIST_NEXT_PATH_API_KEY)
        isOnline = intent.getBooleanExtra(Constants.IS_ONLINE_KEY, false)
        binding.buttonSaveResponses.isEnabled = isOnline
    }

    /** Initialize the recycler view with layoutManager and pokemonAdapter */
    private fun initRecyclerView() {
        pokemonAdapter = PokemonAdapter(listPokemons)
        binding.recyclerViewPokemon.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPokemon.adapter = pokemonAdapter
    }

    /**
     * This method call to pokemonViewModel to save the data of responses in a file txt
     */
    private fun saveResponses() {
        pokemonVewModel.saveResponses()
    }

}