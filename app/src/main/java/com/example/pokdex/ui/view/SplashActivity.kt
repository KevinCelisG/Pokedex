package com.example.pokdex.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.pokdex.R
import com.example.pokdex.core.Constants
import com.example.pokdex.databinding.ActivitySplashBinding
import com.example.pokdex.domain.model.Pokemon
import com.example.pokdex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val pokemonVewModel: PokemonViewModel by viewModels()
    private var listPokemons = ArrayList<Pokemon>()
    private var nextPage: String? = ""
    private var isOnline = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonVewModel.getPokemonsResponse(Constants.INIT_PAGE_PATH)

        pokemonVewModel.isLoading.observe(this, Observer {
            binding.progressCircularSplash.isVisible = it
        })

        pokemonVewModel.listPokemons.observe(this, Observer {
            listPokemons.clear()
            listPokemons.addAll(it)
            println("Cantidad de pokemones total: ${listPokemons.size}")
            println("Cantidad de pokemones} llegando: ${it.size}")
            goToHome()
        })

        pokemonVewModel.listPokemonsResponse.observe(this, Observer {
            if (it != null) {
                pokemonVewModel.clearAllPokemons()
                isOnline = true
            }
            nextPage = it?.next?.replace(Constants.ROOT_PATH, "")
            pokemonVewModel.getPokemons(it)
        })
    }

    private fun goToHome() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra(Constants.LIST_POKEMON_KEY, listPokemons)
        intent.putExtra(Constants.LIST_NEXT_PATH_API_KEY, nextPage)
        intent.putExtra(Constants.IS_ONLINE_KEY, isOnline)
        startActivity(intent)
    }
}