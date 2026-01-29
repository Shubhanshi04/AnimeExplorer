package com.shubhanshi.animeexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubhanshi.animeexplorer.data.AnimeRepository

class AnimeListViewModelFactory(
    private val repository: AnimeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeListViewModel::class.java)) {
            return AnimeListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}