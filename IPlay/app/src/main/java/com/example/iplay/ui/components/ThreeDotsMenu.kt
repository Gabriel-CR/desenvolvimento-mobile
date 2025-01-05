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
    modifier = Modifier
      .background(MaterialTheme.colorScheme.surface)
  ) {
    DropdownMenuItem(
      text = { Text("Tela Inicial") },
      onClick = {
        expanded = false
        navController.navigate("home")
      }
    )

    DropdownMenuItem(
      text = { Text("Favoritos") },
      onClick = {
        expanded = false
        navController.navigate("favorites")
      }
    )

    DropdownMenuItem(
      text = { Text("Configurações") },
      onClick = {
        expanded = false
        navController.navigate("configuration")
      }
    )

    DropdownMenuItem(
      text = { Text("Ajuda") },
      onClick = {
        expanded = false
        navController.navigate("help")
      }
    )

    DropdownMenuItem(
      text = { Text("Logout") },
      onClick = {
        expanded = false
        navController.navigate("logout")
      }
    )
  }
}
