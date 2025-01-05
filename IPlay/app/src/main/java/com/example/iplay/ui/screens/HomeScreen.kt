package com.example.iplay.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.CardView

@SuppressLint("RememberReturnType")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
  navController: NavHostController,
  viewModel: GameViewModel
) {
  val games by viewModel.gamesView

  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier.padding(16.dp)
  ) {
    items(games) { game ->
      CardView(navController, game)
    }
  }
}
