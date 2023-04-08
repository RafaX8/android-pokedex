package com.rafael.mardom.features.pokedex.presentation.adapter

import android.view.View
import android.view.View.*
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.rafael.mardom.R
import com.rafael.mardom.app.extensions.loadUrl
import com.rafael.mardom.databinding.ViewItemPokedexPokemonBinding
import com.rafael.mardom.features.pokedex.presentation.PokedexListViewModel.*

class PokedexListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ItemUiState, itemClick: ((Int) -> Unit)?) {
        val pokemon = item.pokemonItem
        val binding = ViewItemPokedexPokemonBinding.bind(view)
        val context = view.context
        val typeColorPair = mapOf<String, Int>(
            "normal" to context.getColor(R.color.normal),
            "fire" to context.getColor(R.color.fire),
            "water" to context.getColor(R.color.water),
            "electric" to context.getColor(R.color.electric),
            "grass" to context.getColor(R.color.grass),
            "ice" to context.getColor(R.color.ice),
            "fighting" to context.getColor(R.color.fighting),
            "poison" to context.getColor(R.color.poison),
            "ground" to context.getColor(R.color.ground),
            "flying" to context.getColor(R.color.flying),
            "psychic" to context.getColor(R.color.psychic),
            "bug" to context.getColor(R.color.bug),
            "rock" to context.getColor(R.color.rock),
            "ghost" to context.getColor(R.color.ghost),
            "dragon" to context.getColor(R.color.dragon),
            "dark" to context.getColor(R.color.dark),
            "steel" to context.getColor(R.color.steel),
            "fairy" to context.getColor(R.color.fairy),
        )

        binding.apply {
            pokemonId.text = pokemon.id.toString()
            pokemonName.text = pokemon.name
            pokemonSprite.loadUrl(pokemon.sprite)

            pokemonType1.apply {
                val type1 = pokemon.types[0]
                text = type1
                typeColorPair[type1]?.let { color ->
                    background.setTint(color)
                }
            }
            pokemonType2.apply {
                if (pokemon.types.size > 1) {
                    val type2 = pokemon.types[1]
                    text = type2
                    typeColorPair[type2]?.let { color ->
                        pokemonType2.background.setTint(color)
                    }
                    visibility = VISIBLE
                } else {
                    visibility = GONE
                }
            }
        }
        view.setOnClickListener {
            itemClick?.invoke(pokemon.id)
        }
    }
}