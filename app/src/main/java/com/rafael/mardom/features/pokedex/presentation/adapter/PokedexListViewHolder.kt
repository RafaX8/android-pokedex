package com.rafael.mardom.features.pokedex.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rafael.mardom.app.extensions.loadUrl
import com.rafael.mardom.databinding.ViewItemPokedexPokemonBinding
import com.rafael.mardom.features.pokedex.presentation.PokedexListViewModel.*

class PokedexListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ItemUiState, itemClick: ((Int) -> Unit)?) {
        val pokemon = item.pokemonItem
        val binding = ViewItemPokedexPokemonBinding.bind(view)

        binding.pokemonNumber.text = pokemon.id.toString()
        binding.pokemonName.text = pokemon.name
        binding.pokemonSprite.loadUrl(pokemon.sprite)
        binding.pokemonTyping1.text = pokemon.types[0]
        if (pokemon.types.size > 1) binding.pokemonTyping2.text = pokemon.types[1] else ""
        view.setOnClickListener {
            itemClick?.invoke(pokemon.id)
        }
    }
}