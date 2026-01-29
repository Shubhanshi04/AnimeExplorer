package com.shubhanshi.animeexplorer.data.remote.dto

data class ImagesDto(
    val jpg: JpgDto?
)

data class JpgDto(
    val image_url: String?
)
