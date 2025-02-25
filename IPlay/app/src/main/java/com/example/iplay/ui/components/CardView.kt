package com.example.iplay.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.models.Game
import com.example.iplay.ui.theme.ButtonPrimaryColors
import com.example.iplay.ui.theme.PrimaryTextColor
import com.example.iplay.ui.theme.SecondaryTextColor

@Composable
fun CardView(
  navController: NavController,
  game: Game,
  onSetNotification: (Game) -> Unit
) {
  var isExpanded by remember { mutableStateOf(false) }

  var isShaking by remember { mutableStateOf(false) }
  val rotation by animateFloatAsState(
    targetValue = if (isShaking) 15f else 0f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 200, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    finishedListener = { isShaking = false }
  )

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { isExpanded = !isExpanded }
      .padding(8.dp),
    shape = RoundedCornerShape(8.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
          painter = painterResource(id = game.imageRes),
          contentDescription = game.name,
          modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = game.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = PrimaryTextColor
          )
          Text(
            text = game.sport,
            style = MaterialTheme.typography.bodyMedium,
            color = PrimaryTextColor
          )
        }

        IconButton(
          onClick = {
            onSetNotification(game)
            isShaking = true
          }
        ) {
          Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Set Notification",
            modifier = Modifier.graphicsLayer(
              rotationZ = rotation
            ),
            tint = MaterialTheme.colorScheme.primary
          )
        }
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
          Text(
            text = "Data: ${game.date}",
            style = MaterialTheme.typography.bodySmall,
            color = SecondaryTextColor
          )

          Text(
            text = "Horário: ${game.time}",
            style = MaterialTheme.typography.bodySmall,
            color = SecondaryTextColor
          )

          Spacer(modifier = Modifier.height(16.dp))

          Button(
            onClick = { navController.navigate("gameDetails/${game.id}") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonPrimaryColors
          ) {
            Text("Mostrar Mais Detalhes")
          }
        }
      }

      if (isExpanded) {
        Spacer(modifier = Modifier.height(16.dp))
      }
    }
  }
}
