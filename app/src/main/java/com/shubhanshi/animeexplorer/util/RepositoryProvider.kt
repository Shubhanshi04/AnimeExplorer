package com.shubhanshi.animeexplorer.util

import android.content.Context
import com.shubhanshi.animeexplorer.data.AnimeRepository
import com.shubhanshi.animeexplorer.data.remote.api.RetrofitInstance

object RepositoryProvider {

    fun provideAnimeRepository(context: Context): AnimeRepository {
        val database = DatabaseProvider.provideDatabase(context)
        return AnimeRepository(
            api = RetrofitInstance.api,
            dao = database.animeDao()
        )
    }
}