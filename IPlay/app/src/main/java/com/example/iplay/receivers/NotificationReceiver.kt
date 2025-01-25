package com.example.iplay.receivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.iplay.R

class NotificationReceiver : BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val gameName = intent.getStringExtra("GAME_NAME") ?: "Jogo"
    val notificationId = intent.getIntExtra("NOTIFICATION_ID", 0)

    val notificationManager =
      context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(context, "GAME_CHANNEL")
      .setSmallIcon(R.drawable.logo)
      .setContentTitle("Lembrete de Jogo")
      .setContentText("Não perca o jogo: $gameName!")
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .build()

    notificationManager.notify(notificationId, notification)
  }
}
