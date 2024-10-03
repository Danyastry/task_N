package com.example.testnitrix.domain.repository

import com.example.testnitrix.data.model.VideoResponseItem
import com.example.testnitrix.presentation.state.VideoState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getVideos(): Flow<VideoState<List<VideoResponseItem>>>
}