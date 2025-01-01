package com.example.alarmapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class AlarmService : Service() {

  override fun onBind(intent: Intent?): IBinder? = null

  @SuppressLint("ForegroundServiceType")
  override fun onCreate() {
    super.onCreate()

    val channelId = "alarm_channel"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        channelId,
        "Alarm Notifications",
        NotificationManager.IMPORTANCE_HIGH
      )
      val manager = getSystemService(NotificationManager::class.java)
      manager?.createNotificationChannel(channel)
    }

    val stopIntent = Intent(this, AlarmService::class.java).apply {
      action = "STOP_ALARM"
    }

    val stopPendingIntent = PendingIntent.getService(
      this,
      0,
      stopIntent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val notification = NotificationCompat.Builder(this, channelId)
      .setContentTitle("Alarme Ativo")
      .setContentText("Seu alarme está tocando.")
      .setSmallIcon(R.drawable.icon_alarm)
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .setAutoCancel(true)
      .setOngoing(false)
      .addAction(R.drawable.icon_alarm, "Parar", stopPendingIntent)
      .build()

    startForeground(1, notification)
  }

  override fun onDestroy() {
    super.onDestroy()
    stopForeground(STOP_FOREGROUND_REMOVE)
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    if (intent?.action == "STOP_ALARM") {
      stopSelf()
    }
    return START_NOT_STICKY
  }
}
