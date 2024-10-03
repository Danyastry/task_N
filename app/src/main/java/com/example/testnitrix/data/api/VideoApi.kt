package com.example.testnitrix.data.api

import com.example.testnitrix.data.model.VideoResponseItem
import retrofit2.http.GET

const val BASE_URL = "https://gist.githubusercontent.com/poudyalanil/"

interface VideoApi {
    @GET("ca84582cbeb4fc123a13290a586da925/raw/14a27bd0bcd0cd323b35ad79cf3b493dddf6216b/videos.json")
    suspend fun getAllVideos(): List<VideoResponseItem>
}