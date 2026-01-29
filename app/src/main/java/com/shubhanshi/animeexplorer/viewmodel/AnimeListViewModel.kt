package com.shubhanshi.animeexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhanshi.animeexplorer.data.AnimeRepository
import com.shubhanshi.animeexplorer.domain.model.Anime
import com.shubhanshi.animeexplorer.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository : AnimeRepository
): ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Anime>>>(UiState.Loading)
    val state : StateFlow<UiState<List<Anime>>> = _state

    fun loadAnime(isOnline: Boolean) {
        viewModelScope.launch {
            try{
                _state.value = UiState.Loading
                val data = repository.getTopAnime(isOnline)
                _state.value = UiState.Success(data)
            } catch(e: Exception) {
                _state.value = UiState.Error("Failed to load Anime")
            }
        }
    }
}