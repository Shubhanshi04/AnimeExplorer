package com.shubhanshi.animeexplorer.data.remote.dto

data class AnimeDetailsDto(
    val id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val images: ImagesDto,
    val genres: List<GenreDto>,
    val trailer: TrailerDto?
)

data class GenreDto(val name: String)
data class TrailerDto(val youtube_id: String?)
