package com.example.testnitrix.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testnitrix.data.model.VideoResponseItem
import com.example.testnitrix.domain.repository.Repository
import com.example.testnitrix.presentation.state.VideoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _videos = MutableStateFlow<VideoState<List<VideoResponseItem>>>(VideoState.Loading)
    val videos: StateFlow<VideoState<List<VideoResponseItem>>> = _videos

    init {
        fetchVideos()
    }

    private fun fetchVideos() {
        viewModelScope.launch {
            repository.getVideos().collect {
                _videos.value = it
            }
        }
    }

}