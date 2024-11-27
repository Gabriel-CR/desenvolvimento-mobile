package com.example.zooapp.ui.components

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

@Composable
fun CampoFutebolListItem(campoFutebol: CampoFutebol, onCampoFutebolSelected: (CampoFutebol) -> Unit) {
  Card(
    modifier = Modifier.fillMaxWidth().padding(8.dp),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Image(
          painter = painterResource(id = campoFutebol.imageRes),
          contentDescription = "${campoFutebol.name} Image",
          modifier = Modifier.size(80.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
          Text(text = campoFutebol.name, style = MaterialTheme.typography.titleMedium)
          Text(text = campoFutebol.location, style = MaterialTheme.typography.bodySmall)
        }
      }
      Spacer(modifier = Modifier.width(16.dp))

      Text(
        text = "Horário de funcionamento: ${campoFutebol.availability}",
        style = MaterialTheme.typography.bodyMedium
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Dimensões: ${campoFutebol.dimensions}",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.secondary
      )

      Spacer(modifier = Modifier.height(8.dp))

      Button(onClick = { onCampoFutebolSelected(campoFutebol) }) {
        Text("Mais sobre ${campoFutebol.name}")
      }
    }
  }
}