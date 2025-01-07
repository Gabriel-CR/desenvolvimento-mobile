package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoPlayerScreen(
  videoResId: Int,
  navController: NavController
) {
  val context = LocalContext.current
  val exoPlayer = remember {
    ExoPlayer.Builder(context).build().apply {
      val mediaItem = MediaItem.fromUri(
        RawResourceDataSource.buildRawResourceUri(videoResId)
      )
      setMediaItem(mediaItem)
      prepare()
    }
  }

  DisposableEffect(Unit) {
    onDispose {
      exoPlayer.release()
    }
  }

  Box(
    modifier = Modifier.fillMaxSize()
  ) {
    AndroidView(
      factory = {
        PlayerView(context).apply {
          player = exoPlayer
        }
      },
      modifier = Modifier.fillMaxSize()
    )

    IconButton(
      onClick = { navController.popBackStack() },
      modifier = Modifier
        .align(Alignment.TopStart)
        .padding(16.dp)
        .size(48.dp)
    ) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Voltar",
        tint = MaterialTheme.colorScheme.primary
      )
    }
  }
}

