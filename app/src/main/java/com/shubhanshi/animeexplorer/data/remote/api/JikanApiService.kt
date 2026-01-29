package com.shubhanshi.animeexplorer.data.remote.api

import com.shubhanshi.animeexplorer.data.remote.dto.AnimeDetailsDto
import com.shubhanshi.animeexplorer.data.remote.dto.AnimeDto
import com.shubhanshi.animeexplorer.data.remote.dto.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService {
    @GET("v4/top/anime")
    suspend fun getTopAnime(): ApiResponse<List<AnimeDto>>

    @GET("v4/anime/{id}")
    suspend fun getAnimeDetails(
        @Path("id") id : Int
    ): ApiResponse<AnimeDetailsDto>
}