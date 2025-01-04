package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
  navController: NavController
) {
  val userName = "João Silva"
  val favoriteSports = listOf("Futebol", "Basquete", "Vôlei")
  val upcomingGames = listOf(
    "Futebol - Domingo, 14:00",
    "Vôlei - Segunda, 18:00",
    "Basquete - Quarta, 20:00"
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    contentAlignment = Alignment.TopCenter
  ) {
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      item {
        Text(
          text = "Bem-vindo, $userName",
          style = MaterialTheme.typography.titleLarge
        )
      }
      item {
        Text(
          text = "Esportes Favoritos:",
          style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        favoriteSports.forEach { sport ->
          Text(
            text = "- $sport",
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
      item {
        Text(
          text = "Próximos Jogos:",
          style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        upcomingGames.forEach { game ->
          Text(
            text = "- $game",
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
      item {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.fillMaxWidth()
        ) {
          Spacer(modifier = Modifier.height(16.dp))

          Button(
            onClick = { navController.navigate("logout") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = "Sair",
              color = MaterialTheme.colorScheme.onError
            )
          }
        }
      }
    }
  }
}
