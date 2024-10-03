package com.example.testnitrix.presentation.player

import android.app.Activity
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.testnitrix.presentation.player.components.ControlButton

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    videoUrl: List<String>,
    currentIndex: Int,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity
    var player by remember { mutableStateOf<ExoPlayer?>(null) }

    if (player == null) {
        player = ExoPlayer.Builder(context).build().apply {
            val mediaItems = videoUrl.map { url ->
                MediaItem.fromUri(Uri.parse(url))
            }
            setMediaItems(mediaItems, currentIndex, C.TIME_UNSET)
            playWhenReady = true
            prepare()
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            player?.release()
            player = null
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        this.player = player
                        controllerAutoShow = true
                        keepScreenOn = true
                        setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                        setFullscreenButtonClickListener { isFullscreen ->
                            if (isFullscreen) {
                                activity.requestedOrientation = SCREEN_ORIENTATION_USER_LANDSCAPE
                            } else {
                                activity.requestedOrientation = SCREEN_ORIENTATION_USER
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            ControlButton("Back", onBack)
        }
    }
}

