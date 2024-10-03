package com.example.testnitrix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoResponseItem(
    @PrimaryKey val id: String,
    val thumbnailUrl: String,
    val title: String,
    val videoUrl: String,
    val views: String
)