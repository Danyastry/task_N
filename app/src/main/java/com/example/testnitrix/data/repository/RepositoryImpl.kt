package com.example.testnitrix.data.repository

import coil.network.HttpException
import com.example.testnitrix.data.api.VideoApi
import com.example.testnitrix.data.local.Dao
import com.example.testnitrix.data.model.VideoResponseItem
import com.example.testnitrix.domain.repository.Repository
import com.example.testnitrix.presentation.state.VideoState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RepositoryImpl(private val api: VideoApi, private val dao: Dao) : Repository {
    override suspend fun getVideos(): Flow<VideoState<List<VideoResponseItem>>> =
        flow {
            emit(VideoState.Loading)
            val cachedVideos = dao.getVideos()
            if (cachedVideos.isNotEmpty()) {
                emit(VideoState.Success(cachedVideos))
            }
            try {
                val videos = api.getAllVideos()
                val duplicatedVideos = videos + videos.take(8)
                dao.insertVideos(duplicatedVideos.map {
                    VideoResponseItem(
                        id = it.id,
                        title = it.title,
                        videoUrl = it.videoUrl,
                        thumbnailUrl = it.thumbnailUrl,
                        views = it.views
                    )
                })
                emit(VideoState.Success(duplicatedVideos))
            } catch (e: HttpException) {
                emit(VideoState.Error("Server error: ${e.message}"))
            } catch (e: IOException) {
                emit(VideoState.Error("Network error: ${e.message}"))
            } catch (e: Exception) {
                emit(VideoState.Error("Unexpected error: ${e.message}"))
            }
        }
}