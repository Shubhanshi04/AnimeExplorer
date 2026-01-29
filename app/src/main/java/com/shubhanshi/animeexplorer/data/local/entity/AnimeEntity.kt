package com.shubhanshi.animeexplorer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id : Int,
    val title : String,
    val imageUrl : String,
    val rating : Double?,
    val episodes : Int
)
