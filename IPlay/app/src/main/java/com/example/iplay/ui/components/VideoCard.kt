package com.example.iplay.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.File

@Composable
fun VideoCard(
  videoResId: Int,
  navController: NavController,
  context: Context
) {
  val thumbnail = remember {
    getVideoThumbnail(context, videoResId)?.asImageBitmap()
  }

  Card(
    modifier = Modifier
      .size(200.dp)
      .padding(4.dp),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
        .clickable {
          navController.navigate("videoPlayer/$videoResId")
        }
    ) {
      if (thumbnail != null) {
        Image(
          bitmap = thumbnail,
          contentDescription = "Thumbnail do vídeo",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )
      } else {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Carregando...",
            style = MaterialTheme.typography.bodySmall
          )
        }
      }

      Box(
        modifier = Modifier
          .align(Alignment.Center)
          .background(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            shape = CircleShape
          )
          .padding(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.PlayArrow,
          contentDescription = "Play",
          tint = MaterialTheme.colorScheme.onPrimary,
          modifier = Modifier.size(32.dp)
        )
      }
    }
  }
}

fun getVideoThumbnail(context: Context, rawResId: Int): Bitmap? {
  val videoFile = copyRawToCache(context, rawResId, "video_$rawResId.mp4")
  return ThumbnailUtils.createVideoThumbnail(
    videoFile.absolutePath,
    MediaStore.Video.Thumbnails.MINI_KIND
  )
}

fun copyRawToCache(context: Context, rawResId: Int, fileName: String): File {
  val inputStream = context.resources.openRawResource(rawResId)
  val cacheFile = File(context.cacheDir, fileName)
  inputStream.use { input ->
    cacheFile.outputStream().use { output ->
      input.copyTo(output)
    }
  }
  return cacheFile
}
