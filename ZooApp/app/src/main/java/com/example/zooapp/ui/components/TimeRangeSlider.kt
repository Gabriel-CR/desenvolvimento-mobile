package com.example.zooapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimeRangeSlider(
  timeRange: ClosedFloatingPointRange<Float>,
  onTimeRangeChange: (ClosedFloatingPointRange<Float>) -> Unit,
  modifier: Modifier = Modifier
) {
  var sliderValues by remember { mutableStateOf(timeRange) }

  Column(modifier.padding(8.dp)) {
    Text(
      text = "Pesquisar por horário",
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(bottom = 8.dp),
      color = Color.White
    )

    Text(
      text = "${formatTime(sliderValues.start)} - ${formatTime(sliderValues.endInclusive)}",
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.padding(bottom = 8.dp),
      color = Color.White
    )

    RangeSlider(
      value = sliderValues,
      onValueChange = {
        sliderValues = it.start..it.endInclusive
        onTimeRangeChange(sliderValues)
      },
      valueRange = 0f..24f,
      steps = 60,
      modifier = Modifier.fillMaxWidth()
    )
  }
}


@SuppressLint("DefaultLocale")
fun formatTime(value: Float): String {
  val hours = value.toInt()
  val minutes = ((value - hours) * 60).toInt()
  return String.format("%02d:%02d", hours, minutes)
}

