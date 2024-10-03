package com.example.testnitrix.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testnitrix.data.model.VideoResponseItem

@Dao
interface Dao {

    @Query("SELECT * FROM videos")
    suspend fun getVideos(): List<VideoResponseItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoResponseItem>)

}