package com.example.iplay.ui.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*

@SuppressLint("DefaultLocale")
@Composable
fun DatePickerField(
  selectedDate: String,
  onDateSelected: (String) -> Unit
) {
  var showDatePicker by remember { mutableStateOf(false) }

  OutlinedButton(
    onClick = { showDatePicker = true },
    modifier = Modifier.fillMaxWidth()
  ) {
    Text(text = if (selectedDate.isNotEmpty()) selectedDate else "Selecionar Data")
  }

  if (showDatePicker) {
    val calendar = Calendar.getInstance()
    DatePickerDialog(
      LocalContext.current,
      { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        onDateSelected(formattedDate)
        showDatePicker = false
      },
      calendar.get(Calendar.YEAR),
      calendar.get(Calendar.MONTH),
      calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
  }
}
