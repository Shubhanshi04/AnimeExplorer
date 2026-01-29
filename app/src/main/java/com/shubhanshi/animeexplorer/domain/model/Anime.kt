package com.shubhanshi.animeexplorer.domain.model

data class Anime(
    val id : Int,
    val title : String,
    val imageURL : String,
    val rating : Double?,
    val episodes : Int
)
