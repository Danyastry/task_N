package com.example.testnitrix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testnitrix.data.model.VideoResponseItem
import com.example.testnitrix.presentation.main.MainScreen
import com.example.testnitrix.presentation.main.MainViewModel
import com.example.testnitrix.presentation.player.PlayerScreen
import com.example.testnitrix.presentation.state.VideoState
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(viewModel: MainViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val videoState = viewModel.videos.collectAsState()

    NavHost(navController = navController, startDestination = Route.MAIN) {

        composable(Route.MAIN) {
            MainScreen(navController = navController)
        }

        composable(
            Route.VIDEO_PLAYER,
            arguments = listOf(navArgument(Route.CURRENT_INDEX) { type = NavType.IntType })
        ) { navBackStackEntry ->

            val currentIndex = navBackStackEntry.arguments?.getInt(Route.CURRENT_INDEX) ?: 0

            if (videoState.value is VideoState.Success) {
                val videos = (videoState.value as VideoState.Success<List<VideoResponseItem>>).data
                PlayerScreen(
                    videoUrl = videos.map { it.videoUrl },
                    currentIndex = currentIndex,
                    onBack = { navController.navigate(Route.MAIN) }
                )
            }
        }
    }
}