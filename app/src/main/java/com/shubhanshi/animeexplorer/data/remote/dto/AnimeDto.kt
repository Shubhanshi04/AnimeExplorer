package com.shubhanshi.animeexplorer.data.remote.dto

data class AnimeDto(
    val id: Int,
    val title: String,
    val episodes: Int,
    val score: Double?,
    val images: ImagesDto
)

data class ImagesDto(val jpg: JpgDto)
data class JpgDto(val image_url: String)
