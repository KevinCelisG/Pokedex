package com.example.pokdex.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
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
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonVewModel: PokemonViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var listPokemons: ArrayList<Pokemon>
    private var isOnline: Boolean = false
    private var nextPage: String? = ""

    /**
     * Call to other methods to initData and initRecyclerView also manage the observes of pokemonViewModel
     */
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initRecyclerView()

        binding.buttonSaveResponses.setOnClickListener {
            saveResponses()
        }

        pokemonVewModel.listPokemonsResponse.observe(this) {
            nextPage = it?.next?.replace(Constants.ROOT_PATH, "").toString()
            pokemonVewModel.getPokemons(it)
        }

        pokemonVewModel.listPokemons.observe(this) {
            listPokemons = Utils.addDataToArrayList(listPokemons, it as ArrayList<Pokemon>)
            pokemonAdapter.notifyDataSetChanged()
        }

        pokemonVewModel.isLoading.observe(this) {
            binding.progressCircular.isVisible = it
        }

        binding.nestedScrollViewList.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (nextPage != null) {
                    pokemonVewModel.getPokemonsResponse(nextPage!!)
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.message_empty_pokemons),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    /**
     * Initialize the listPokemons, nextPage and isOnline and configure to buttonSaveResponse
     */
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
     * Call to pokemonViewModel to save the data of responses in a file txt
     */
    private fun saveResponses() {
        pokemonVewModel.saveResponses()
    }

}