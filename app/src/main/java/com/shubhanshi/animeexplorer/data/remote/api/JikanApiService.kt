package com.shubhanshi.animeexplorer.data.remote.api

import retrofit2.http.GET

interface JikanApiService {
    @GET("v4/top/anime")
    suspend fun getTopAnime(): ApiResponse<List<AnimeDto>>

}