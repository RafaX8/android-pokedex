package com.rafael.mardom.features.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.features.pokedex.domain.FilterFavoritesFeedUseCase
import com.rafael.mardom.features.pokedex.domain.GetAllPokemonUseCase
import com.rafael.mardom.features.pokedex.domain.GetAllPokemonUseCase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexListViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    private val filterFavoritesFeedUseCase: FilterFavoritesFeedUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    var currentUiState = UiState()

    private var jobPokedex: Job? = null
    private var jobFavorites: Job? = null

    fun loadPokedex() {
        jobFavorites?.cancel()
        currentUiState = currentUiState.copy(isLoading = true)
        _uiState.postValue(currentUiState)
        jobPokedex = viewModelScope.launch(Dispatchers.IO) {
            getAllPokemonUseCase.invoke().fold({
                errorState(it)
            }, {
                successState(it)
            })
        }
    }
    fun loadFavoritesPokedex() {
        jobPokedex?.cancel()
        currentUiState = currentUiState.copy(isLoading = true)
        _uiState.postValue(currentUiState)
        jobFavorites = viewModelScope.launch(Dispatchers.IO) {
            filterFavoritesFeedUseCase.invoke().fold({
                errorState(it)
            }, {
                successState(it)
            })
        }
    }

    private fun errorState(errorApp: ErrorApp) {
        currentUiState = currentUiState.copy(error = errorApp, isLoading = false)
        _uiState.postValue(currentUiState)
    }

    private fun successState(pokedex: List<PokemonItem>) {
        currentUiState = currentUiState.copy(
            isLoading = false,
            pokedex = pokedex.map { pokemon ->
                ItemUiState(
                    pokemonItem = pokemon,
                    isFavorite = pokemon.isFavorite
                )
            },
            error = null
        )
        _uiState.postValue(currentUiState)
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            reload()
        }
    }

    private fun reload() {
        if (currentUiState.areFavoritesLoaded) {
            loadFavoritesPokedex()
        } else {
            loadPokedex()
        }
    }

    data class UiState(
        val error: ErrorApp? = null,
        val isLoading: Boolean = false,
        val pokedex: List<ItemUiState>? = null,
        var areFavoritesLoaded: Boolean = false,
    )

    data class ItemUiState(
        val isFavorite: Boolean,
        val pokemonItem: PokemonItem,
    )
}