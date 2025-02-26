package com.example.iplay.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable

sealed class BottomBarScreen(
  val route: String,
  val icon:
  @Composable () -> Unit,
  val label: String
) {
  object Games : BottomBarScreen(
    route = "games",
    icon = {
      androidx.compose.material3.Icon(
        Icons.Default.DateRange,
        contentDescription = "Jogos"
      )
    },
    label = "Jogos"
  )

  object Profile : BottomBarScreen(
    route = "profile",
    icon = {
      androidx.compose.material3.Icon(
        Icons.Default.Person,
        contentDescription = "Perfil"
      )
    },
    label = "Perfil"
  )

  object Search : BottomBarScreen(
    route = "search",
    icon = {
      androidx.compose.material3.Icon(
        Icons.Default.Search,
        contentDescription = "Buscar"
      )
    },
    label = "Buscar"
  )

  object Chat : BottomBarScreen(
    route = "chat",
    icon = {
      androidx.compose.material3.Icon(
        Icons.Default.MailOutline,
        contentDescription = "Chat"
      )
    },
    label = "Chat"
  )
}

val screens = listOf(
  BottomBarScreen.Games,
  BottomBarScreen.Profile,
  BottomBarScreen.Search,
  BottomBarScreen.Chat
)