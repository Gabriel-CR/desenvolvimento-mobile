package com.example.iplay.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.iplay.ui.theme.PrimaryTextColor

@Composable
fun ThreeDotsMenu(
  navController: NavController
) {
  var expanded by remember { mutableStateOf(false) }

  IconButton(onClick = { expanded = true }) {
    Icon(Icons.Default.MoreVert, contentDescription = "Menu")
  }

  DropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false },
  ) {
    DropdownMenuItem(
      text = { Text(text = "Tela Inicial", color = PrimaryTextColor) },
      onClick = {
        expanded = false
        navController.navigate("home")
      }
    )

    DropdownMenuItem(
      text = { Text(text = "Favoritos", color = PrimaryTextColor) },
      onClick = {
        expanded = false
        navController.navigate("favorites")
      }
    )

    DropdownMenuItem(
      text = { Text(text = "Configurações", color = PrimaryTextColor) },
      onClick = {
        expanded = false
        navController.navigate("settings")
      }
    )

    DropdownMenuItem(
      text = { Text(text = "Ajuda", color = PrimaryTextColor) },
      onClick = {
        expanded = false
        navController.navigate("help")
      }
    )

    DropdownMenuItem(
      text = { Text(text = "Logout", color = PrimaryTextColor) },
      onClick = {
        expanded = false
        navController.navigate("logout")
      }
    )
  }
}
