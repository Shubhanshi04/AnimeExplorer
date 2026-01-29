package com.shubhanshi.animeexplorer.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhanshi.animeexplorer.data.AnimeRepository
import com.shubhanshi.animeexplorer.data.remote.dto.AnimeDetailsDto
import com.shubhanshi.animeexplorer.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel (
    private val repository: AnimeRepository
): ViewModel() {
    private val _state = MutableStateFlow<UiState<AnimeDetailsDto>>(UiState.Loading)
    val state : StateFlow<UiState<AnimeDetailsDto>> =_state

    fun loadAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            try{
                _state.value = UiState.Loading
                val details = repository.getAnimeDetails(animeId)
                _state.value = UiState.Success(details)
            } catch(e: Exception) {
                _state.value = UiState.Error("Failed to load anime details")
            }
        }
    }
}