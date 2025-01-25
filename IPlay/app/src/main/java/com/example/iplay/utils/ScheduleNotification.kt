package com.example.iplay.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.iplay.models.Game
import com.example.iplay.receivers.NotificationReceiver
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("ServiceCast", "ScheduleExactAlarm")
fun scheduleNotification(context: Context, game: Game) {
  val intent = Intent(context, NotificationReceiver::class.java).apply {
    putExtra("GAME_NAME", game.name)
    putExtra("NOTIFICATION_ID", game.id)
  }

  val pendingIntent = PendingIntent.getBroadcast(
    context,
    game.id,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
  )

  val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

  // Definir o horário da notificação
  val notificationTime = Calendar.getInstance().apply {
    time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).parse("${game.date} ${game.time}")!!
    Log.d("scheduleNotification", "Tempo configurado ${time}")
  }.timeInMillis

  alarmManager.setExact(
    AlarmManager.RTC_WAKEUP,
    notificationTime,
    pendingIntent
  )
}