package com.example.tasksmanagerapp.ui.components

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.tasksmanagerapp.MainActivity
import com.example.tasksmanagerapp.ui.views.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
  context: Context,
  viewModel: TasksViewModel
) {
  TopAppBar(
    title = { Text("Gerenciador de Tarefas") },
    actions = {
      IconButton(onClick = { viewModel.toggleTheme(context) }) {
        Icon(Icons.Default.BrightnessHigh, contentDescription = "Alternar Tema")
      }
    },
    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF6200EE))
  )
}