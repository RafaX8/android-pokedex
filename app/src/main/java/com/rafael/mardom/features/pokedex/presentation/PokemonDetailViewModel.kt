package com.rafael.mardom.features.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.features.pokedex.domain.DeleteFavoriteUseCase
import com.rafael.mardom.features.pokedex.domain.GetPokemonByIdUseCase
import com.rafael.mardom.features.pokedex.domain.GetPokemonByIdUseCase.*
import com.rafael.mardom.features.pokedex.domain.SaveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    var currentUiState = UiState()

    fun loadAnimal(id: Int) {
        currentUiState = currentUiState.copy(isLoading = true)
        _uiState.value = currentUiState
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonByIdUseCase.invoke(id).fold({
                currentUiState = currentUiState.copy(error = it)
                _uiState.postValue(currentUiState)
            }, {
                currentUiState = currentUiState.copy(isLoading = false, pokemon = it, error = null)
                _uiState.postValue(currentUiState)
            })

        }
    }

    fun addToFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            saveFavoriteUseCase.invoke(id)
        }
    }

    fun removeFromFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.invoke(id)
        }
    }

    data class UiState(
        val error: ErrorApp? = null,
        val isLoading: Boolean = false,
        val pokemon: PokemonDetail? = null
    )
}