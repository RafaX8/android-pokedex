package com.rafael.mardom.features.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.features.pokedex.domain.GetAllPokemonUseCase
import com.rafael.mardom.features.pokedex.domain.GetAllPokemonUseCase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexListViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    var currentUiState = UiState()

    private var jobPokedex: Job? = null

    fun loadPokedex() {
        jobPokedex?.cancel()
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
                )
            },
            error = null
        )
        _uiState.postValue(currentUiState)
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokedex()
        }
    }

    data class UiState(
        val error: ErrorApp? = null,
        val isLoading: Boolean = false,
        val pokedex: List<ItemUiState>? = null,
    )

    data class ItemUiState(
        val pokemonItem: PokemonItem,
    )
}