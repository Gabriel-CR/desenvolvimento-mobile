package com.example.iplay.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import java.io.File

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