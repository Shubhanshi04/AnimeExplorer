package com.shubhanshi.animeexplorer.data.remote.dto

data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: ImagesDto?
)
