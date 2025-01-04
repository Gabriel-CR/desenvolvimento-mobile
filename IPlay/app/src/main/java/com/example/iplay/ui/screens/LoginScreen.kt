package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(
  navController: NavController
) {
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Login",
        style = MaterialTheme.typography.titleLarge
      )
      Spacer(modifier = Modifier.height(32.dp))

      OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("Nome de Usuário") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Senha") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(32.dp))

      Button(
        onClick = {
          // Simula navegação após o login
          navController.navigate("home") {
            popUpTo("login") { inclusive = true }
          }
        },
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("Entrar")
      }
    }
  }
}
