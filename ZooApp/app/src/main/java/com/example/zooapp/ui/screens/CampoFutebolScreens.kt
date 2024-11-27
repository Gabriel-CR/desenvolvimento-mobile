package com.example.zooapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.zooapp.models.CampoFutebol

@ExperimentalMaterial3Api
@Composable
fun CampoFutebolScreen(campoFutebol: CampoFutebol) {
  Scaffold(
    topBar = {
      TopAppBar(title = { Text(campoFutebol.name) })
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = campoFutebol.imageRes),
        contentDescription = "${campoFutebol.name} Image",
        modifier = Modifier
          .size(200.dp)
          .clip(CircleShape)
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = campoFutebol.location,
        style = MaterialTheme.typography.titleMedium
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "Dimensões: ${campoFutebol.dimensions}",
        style = MaterialTheme.typography.bodyMedium
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "Capacidade: ${campoFutebol.capacity}",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.secondary
      )

    }
  }
}