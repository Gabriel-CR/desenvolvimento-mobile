package com.example.iplay.ui.components

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*

@SuppressLint("DefaultLocale")
@Composable
fun TimePickerField(
  selectedTime: String,
  onTimeSelected: (String) -> Unit
) {
  var showTimePicker by remember { mutableStateOf(false) }

  OutlinedButton(
    onClick = { showTimePicker = true },
    modifier = Modifier.fillMaxWidth()
  ) {
    Text(text = if (selectedTime.isNotEmpty()) selectedTime else "Selecionar Horário")
  }

  if (showTimePicker) {
    val calendar = Calendar.getInstance()
    TimePickerDialog(
      LocalContext.current,
      { _: TimePicker, hour: Int, minute: Int ->
        val formattedTime = String.format("%02d:%02d", hour, minute)
        onTimeSelected(formattedTime)
        showTimePicker = false
      },
      calendar.get(Calendar.HOUR_OF_DAY),
      calendar.get(Calendar.MINUTE),
      true
    ).show()
  }
}
