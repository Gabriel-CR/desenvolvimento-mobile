package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.CardView

@Composable
fun SearchScreen(
  navController: NavHostController,
  viewModel: GameViewModel
) {
  var searchText by remember { mutableStateOf("") }
  val games by viewModel.gamesView

  val filteredGames = remember(searchText) {
    games.filter { game ->
      game.name.contains(searchText, ignoreCase = true) ||
      game.sport.contains(searchText, ignoreCase = true)
    }
  }

  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp)
  ) {
    OutlinedTextField(
      value = searchText,
      onValueChange = { searchText = it },
      label = { Text("Buscar jogos") },
      modifier = Modifier.fillMaxWidth(),
      singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      if (filteredGames.isEmpty()) {
        item {
          Text(
            text = "Nenhum jogo encontrado.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
          )
        }
      } else {
        items(filteredGames) { game ->
          CardView(navController, game)
        }
      }
    }
  }
}
