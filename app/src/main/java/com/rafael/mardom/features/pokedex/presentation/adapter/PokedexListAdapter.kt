package com.rafael.mardom.features.pokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rafael.mardom.R
import com.rafael.mardom.features.pokedex.presentation.PokedexListViewModel.*
import javax.inject.Inject

class PokedexListAdapter @Inject constructor() :
    ListAdapter<ItemUiState, PokedexListViewHolder>(
        AsyncDifferConfig.Builder(PokemonDiff()).build()
    ) {

    private var itemClick: ((Int) -> Unit)? = null

    fun setOnClickItem(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexListViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_item_pokedex_pokemon, parent, false)
        return PokedexListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokedexListViewHolder, position: Int) {
        holder.bind(currentList[position], itemClick)
    }

    override fun getItemCount(): Int = currentList.size

    class PokemonDiff : DiffUtil.ItemCallback<ItemUiState>() {

        override fun areItemsTheSame(oldItem: ItemUiState, newItem: ItemUiState): Boolean {
            return oldItem.pokemonItem.id == newItem.pokemonItem.id
        }

        override fun areContentsTheSame(oldItem: ItemUiState, newItem: ItemUiState): Boolean {
            return oldItem == newItem
        }
    }
}