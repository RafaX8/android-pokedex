package com.rafael.mardom.features.pokedex.presentation.adapter

import android.view.View
import android.view.View.*
import androidx.recyclerview.widget.RecyclerView
import com.rafael.mardom.R
import com.rafael.mardom.app.extensions.ColorTypePairing
import com.rafael.mardom.app.extensions.loadUrl
import com.rafael.mardom.databinding.ViewItemPokedexPokemonBinding
import com.rafael.mardom.features.pokedex.presentation.PokedexListViewModel.*

class PokedexListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ItemUiState, itemClick: ((Int) -> Unit)?) {
        val pokemon = item.pokemonItem
        val binding = ViewItemPokedexPokemonBinding.bind(view)
        val context = view.context
        val typeColorPair = ColorTypePairing.typeColorPair

        binding.apply {
            pokemonId.text = pokemon.id.toString()
            pokemonName.text = pokemon.name
            pokemonSprite.loadUrl(pokemon.sprite)

            pokemonType1.apply {
                val type1 = pokemon.types[0]
                text = type1
                typeColorPair[type1]?.let { colorId ->
                    background.setTint(context.getColor(colorId))
                }
            }
            pokemonType2.apply {
                if (pokemon.types.size > 1) {
                    val type2 = pokemon.types[1]
                    text = type2
                    typeColorPair[type2]?.let { colorId ->
                        background.setTint(context.getColor(colorId))
                    }
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }
        }
        view.setOnClickListener {
            itemClick?.invoke(pokemon.id)
        }
    }
}