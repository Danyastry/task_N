package com.example.testnitrix.presentation.state


sealed class VideoState<out T> {
    data class Success<T>(val data: T) : VideoState<T>()
    data class Error(val error: String) : VideoState<Nothing>()
    data object Loading : VideoState<Nothing>()
}