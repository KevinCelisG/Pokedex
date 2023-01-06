package com.example.pokdex.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokdex.R
import com.example.pokdex.core.Constants
import com.example.pokdex.data.model.Pokemon
import com.example.pokdex.data.model.adapter.PokemonAdapter
import com.example.pokdex.databinding.ActivityMainBinding
import com.example.pokdex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonVewModel: PokemonViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private var listPokemons = ArrayList<Pokemon>()
    private var nextPage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        pokemonVewModel.listPokemonsResponse.observe(this, Observer {
            println("Funciono chamo :,D")
            println(it.toString())
            nextPage = it.next.replace(Constants.ROOT_PATH, "")
            println("The next path no consume api is: $nextPage")
        })

        pokemonVewModel.listPokemons.observe(this, Observer {
            listPokemons.clear()
            listPokemons.addAll(it)
            pokemonAdapter.notifyDataSetChanged()
        })

        pokemonVewModel.isLoading.observe(this, Observer {
            binding.progressCircular.isVisible = it
        })

        binding.buttonStart.setOnClickListener {
            //pokemonVewModel.getPokemonsResponse(nextPage ?: Constants.INIT_PAGE_PATH)
        }

        binding.nestedScrollViewList.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val lineDead = v.getChildAt(0).measuredHeight - v.measuredHeight
            if (scrollY == lineDead) {
                pokemonVewModel.getPokemonsResponse(nextPage ?: Constants.INIT_PAGE_PATH)
            }
            println("GetChild: ${v.getChildAt(0)}, height child: ${v.getChildAt(0).measuredHeight}, height: ${v.measuredHeight}")
            println("Scroll Y: $scrollY - Calculate: ${v.getChildAt(0).measuredHeight - v.measuredHeight}")
        })

        pokemonVewModel.getPokemonsResponse(nextPage ?: Constants.INIT_PAGE_PATH)
    }

    private fun initRecyclerView() {
        pokemonAdapter = PokemonAdapter(listPokemons)
        binding.recyclerViewPokemon.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPokemon.adapter = pokemonAdapter
    }
}