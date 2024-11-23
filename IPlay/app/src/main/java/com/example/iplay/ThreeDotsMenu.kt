package com.example.iplay

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ThreeDotsMenu() {
  var expanded by remember { mutableStateOf(false) }

  IconButton(onClick = { expanded = true }) {
    Icon(Icons.Default.MoreVert, contentDescription = "Menu")
  }

  DropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false }
  ) {
    val items: List<String> = listOf("Tela Inicial", "Favoritos", "Configurações", "Ajuda")

    Column {
      items.forEach { item ->
        DropdownMenuItem(
          text = { Text(item) },
          onClick = {
            expanded = false
          }
        )
      }
    }
  }
}
