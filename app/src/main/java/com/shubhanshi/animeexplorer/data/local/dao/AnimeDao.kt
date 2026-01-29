package com.shubhanshi.animeexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.shubhanshi.animeexplorer.data.local.entity.AnimeEntity

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    suspend fun getAllAnime() : List<AnimeEntity>
}