package com.example.iplay.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.CardView
import com.example.iplay.utils.scheduleNotification

@SuppressLint("RememberReturnType")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
  navController: NavHostController,
  viewModel: GameViewModel
) {
  val games by viewModel.gamesView
  val context = LocalContext.current

  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier.padding(16.dp)
  ) {
    items(games) { game ->
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
