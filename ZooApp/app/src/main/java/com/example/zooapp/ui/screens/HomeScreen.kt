package com.example.zooapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.zooapp.models.CampoFutebol
import com.example.zooapp.models.camposFutebol
import com.example.zooapp.ui.components.CampoFutebolListItem
import com.example.zooapp.ui.components.TimeRangeSlider

@Composable
fun HomeScreen(onCampoFutebolSelected: (CampoFutebol) -> Unit) {
  var searchQuery by remember { mutableStateOf("") }
  var timeRange by remember { mutableStateOf(0f..24f) }
  val filteredCampoFutebol = remember(timeRange) {
    camposFutebol.filter { campo ->
      campo.availability.split("-").let { times ->
        val startTime = parseTimeToFloat(times[0])
        val endTime = parseTimeToFloat(times[1])
        startTime >= timeRange.start && endTime <= timeRange.endInclusive
      }
    }
  }

  Column {
    TextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      label = { Text("Pesquisar por nome") },
      modifier = Modifier.fillMaxWidth().padding(8.dp)
    )

    TimeRangeSlider(
      timeRange = timeRange,
      onTimeRangeChange = { timeRange = it },
      modifier = Modifier.fillMaxWidth()
    )

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.padding(horizontal = 8.dp)
    ) {
      items(filteredCampoFutebol) { camposFutebol ->
        CampoFutebolListItem(camposFutebol, onCampoFutebolSelected)
      }
    }
  }
}

fun parseTimeToFloat(time: String): Float {
  val parts = time.split(":")
  val hours = parts[0].toFloat()
  val minutes = parts[1].toFloat() / 60
  return hours + minutes
}