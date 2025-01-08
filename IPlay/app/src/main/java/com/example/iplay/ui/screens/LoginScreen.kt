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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.models.UserViewModel
import com.example.iplay.ui.theme.ButtonPrimaryColors
import com.example.iplay.ui.theme.PrimaryTextColor

@Composable
fun LoginScreen(
  navController: NavController,
  userViewModel: UserViewModel
) {
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var errorMessage by remember { mutableStateOf("") }

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
        style = MaterialTheme.typography.titleLarge,
        color = PrimaryTextColor
      )
      Spacer(modifier = Modifier.height(32.dp))

      OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text(text = "Nome de Usuário") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Senha") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(16.dp))

      if (errorMessage.isNotEmpty()) {
        Text(
          text = errorMessage,
          color = MaterialTheme.colorScheme.error,
          style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
      }

      Button(
        onClick = {
          val currentUser = userViewModel.userView.value
          if (currentUser != null && currentUser.password == password) {
            userViewModel.updateUser(currentUser.copy(name = username))
            navController.navigate("home") {
              popUpTo("login") { inclusive = true }
            }
          } else {
            errorMessage = "Senha incorreta. Por favor, tente novamente."
          }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonPrimaryColors
      ) {
        Text(text = "Entrar")
      }
    }
  }
}
