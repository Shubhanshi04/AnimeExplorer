package com.shubhanshi.animeexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubhanshi.animeexplorer.data.AnimeRepository

class AnimeDetailViewModelFactory(
    private val repository: AnimeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeDetailViewModel::class.java)) {
            return AnimeDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
