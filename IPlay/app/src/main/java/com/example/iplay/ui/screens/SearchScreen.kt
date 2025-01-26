package com.example.iplay.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.CardView
import com.example.iplay.utils.scheduleNotification

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchScreen(
  navController: NavHostController,
  viewModel: GameViewModel
) {
  var searchText by remember { mutableStateOf("") }
  val games by viewModel.gamesView
  val context = LocalContext.current

  val filteredGames = remember(searchText) {
    games.filter { game ->
      game.name.contains(searchText, ignoreCase = true) ||
              game.sport.contains(searchText, ignoreCase = true)
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    OutlinedTextField(
      value = searchText,
      onValueChange = { searchText = it },
      label = { Text("Buscar jogos") },
      modifier = Modifier.fillMaxWidth(),
      singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    AnimatedContent(
      targetState = filteredGames,
      transitionSpec = {
        slideInVertically { it } + fadeIn() with
                slideOutVertically { -it } + fadeOut()
      },
      modifier = Modifier.fillMaxSize(), label = ""
    ) { gamesToDisplay ->
      if (gamesToDisplay.isEmpty()) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Nenhum jogo encontrado.",
            style = MaterialTheme.typography.bodyLarge
          )
        }
      } else {
        LazyColumn(
          verticalArrangement = Arrangement.spacedBy(8.dp),
          modifier = Modifier.fillMaxSize()
        ) {
          items(gamesToDisplay) { game ->
            CardView(navController, game, onSetNotification = { selectedGame ->
              scheduleNotification(context = context, game = selectedGame)
              Toast.makeText(
                context,
                "Notificação agendada para ${selectedGame.name}",
                Toast.LENGTH_SHORT
              ).show()
            })
          }
        }
      }
    }
  }
}
