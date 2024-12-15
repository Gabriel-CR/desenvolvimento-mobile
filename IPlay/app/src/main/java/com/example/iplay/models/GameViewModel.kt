package com.example.iplay.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
  private val _games = mutableStateOf<List<Game>>(emptyList())
  val gamesView: State<List<Game>> get() = _games

  init {
    // Inicialize com os dados iniciais
    _games.value = games
  }

  fun toggleFavorite(gameId: Int) {
    _games.value = _games.value.map {
      if (it.id == gameId) it.copy(isFavorite = !it.isFavorite) else it
    }
  }
}

