package com.shubhanshi.animeexplorer.util

import android.content.Context
import androidx.room.Room
import com.shubhanshi.animeexplorer.data.local.database.AnimeDatabase

object DatabaseProvider {

    fun provideDatabase(context: Context) : AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_db"
        ).build()
    }
}