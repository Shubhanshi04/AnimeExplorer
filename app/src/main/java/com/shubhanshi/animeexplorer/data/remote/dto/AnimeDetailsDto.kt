package com.shubhanshi.animeexplorer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeDetailsDto(
    @SerializedName("mal_id")
    val malId: Int,

    val title: String,

    val synopsis: String?,

    val episodes: Int?,

    val score: Double?,

    val images: ImagesDto?,

    val trailer: TrailerDto?,   // 🔥 THIS MUST EXIST

    val genres: List<GenreDto>?
)

data class TrailerDto(

    @SerializedName("youtube_id")
    val youtubeId: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("embed_url")
    val embedUrl: String?
)

data class GenreDto(
    val name: String
)
