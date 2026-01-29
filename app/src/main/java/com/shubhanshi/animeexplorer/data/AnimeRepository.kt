package com.shubhanshi.animeexplorer.data

import com.shubhanshi.animeexplorer.data.local.dao.AnimeDao
import com.shubhanshi.animeexplorer.data.mapper.toDomain
import com.shubhanshi.animeexplorer.data.mapper.toEntity
import com.shubhanshi.animeexplorer.data.remote.api.JikanApiService
import com.shubhanshi.animeexplorer.data.remote.dto.AnimeDetailsDto
import com.shubhanshi.animeexplorer.domain.model.Anime

class AnimeRepository(
    private val api : JikanApiService,
    private val dao : AnimeDao
) {
    suspend fun getTopAnime(isOnline: Boolean): List<Anime> {
        return if (isOnline) {
            val anime = api.getTopAnime().data.map { it.toDomain() }
            dao.insertAnime(anime.map { it.toEntity() })
            anime
        } else {
            dao.getAllAnime().map { it.toDomain() }
        }
    }

    suspend fun getAnimeDetails(animeId:Int): AnimeDetailsDto {
        return api.getAnimeDetails(animeId).data
    }
}