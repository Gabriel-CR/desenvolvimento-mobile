package com.example.iplay.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LogoutScreen(
  navController: NavController
) {
  var isLoading by remember { mutableStateOf(true) }

  LaunchedEffect(Unit) {
    delay(2000) // 2 segundos
    isLoading = false
    navController.navigate("login") {
      popUpTo("logout") { inclusive = true }
    }
  }

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    if (isLoading) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Fazendo logout...")
      }
    }
  }
}
