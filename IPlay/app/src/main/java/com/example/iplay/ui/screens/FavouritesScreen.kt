package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.FavoriteGameItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun FavouritesScreen(
  navController: NavController,
  gameViewModel: GameViewModel
) {
  val favoriteGames = gameViewModel.gamesView.value.filter { it.isFavorite }
  var isLoading by remember { mutableStateOf(false) }
  var loadingGameId by remember { mutableStateOf<Int?>(null) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Favoritos", color = MaterialTheme.colorScheme.primary) },
        navigationIcon = {
          IconButton(onClick = { navController.navigateUp() }) {
            Icon(
              Icons.Default.ArrowBack,
              contentDescription = "Voltar",
              tint = MaterialTheme.colorScheme.primary
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
      )
    },
    content = { innerPadding ->
      if (favoriteGames.isEmpty()) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Nenhum item favoritado ainda.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
          )
        }
      } else {
        LazyColumn(
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          contentPadding = PaddingValues(16.dp)
        ) {
          items(favoriteGames) { game ->
            FavoriteGameItem(
              game = game,
              isLoading = isLoading && loadingGameId == game.id,
              onToggleFavorite = {
                isLoading = true
                loadingGameId = game.id
                CoroutineScope(Dispatchers.Main).launch {
                  delay(2000)
                  isLoading = false
                  loadingGameId = null
                  gameViewModel.toggleFavorite(game.id)
                }
              },
              onNavigateToDetails = { navController.navigate("gameDetails/${game.id}") }
            )
          }
        }
      }
    }
  )
}
