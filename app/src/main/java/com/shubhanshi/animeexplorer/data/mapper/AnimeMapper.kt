package com.shubhanshi.animeexplorer.data.mapper

import com.shubhanshi.animeexplorer.data.local.entity.AnimeEntity
import com.shubhanshi.animeexplorer.data.remote.dto.AnimeDto
import com.shubhanshi.animeexplorer.domain.model.Anime

fun AnimeDto.toDomain() = Anime(
    id = id,
    title = title,
    imageURL = images.jpg.image_url,
    rating = score,
    episodes = episodes
)

fun AnimeEntity.toDomain() = Anime(
    id = id,
    title = title,
    imageURL = imageUrl,
    rating = rating,
    episodes = episodes
)

fun Anime.toEntity() = AnimeEntity(
    id = id,
    title = title,
    imageUrl = imageURL,
    rating = rating,
    episodes = episodes
)