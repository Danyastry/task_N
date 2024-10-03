package com.example.testnitrix.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testnitrix.presentation.common.EmptyScreen
import com.example.testnitrix.presentation.common.VideoItem
import com.example.testnitrix.presentation.common.VideoShimmerEffect
import com.example.testnitrix.presentation.state.VideoState
import com.example.testnitrix.ui.theme.Background
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel(), navController: NavController) {

    val videos = viewModel.videos.collectAsState()
    val lazyState = rememberLazyListState()

    when (val data = videos.value) {

        is VideoState.Error -> {
            EmptyScreen(error = data)
        }

        VideoState.Loading -> {
            LazyColumn(
                state = lazyState,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .background(Background)
            ) {
                items(16) {
                    VideoShimmerEffect()
                }
            }
        }

        is VideoState.Success -> {
            LazyColumn(
                state = lazyState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(top = 50.dp)
            ) {
                items(data.data) { item ->
                    VideoItem(item, data.data.indexOf(item), navController)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
