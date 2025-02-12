package com.example.authapp.ui.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authapp.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.FROYO)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(viewModel: AuthViewModel, navController: NavController) {
  var email by remember { mutableStateOf("") }
  var isEmailValid by remember { mutableStateOf(true) }
  val context = LocalContext.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Cabeçalho
    Text(
      text = "Recuperar Senha",
      fontSize = 24.sp,
      style = MaterialTheme.typography.headlineLarge,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    // Campo de Email
    OutlinedTextField(
      value = email,
      onValueChange = {
        email = it
        isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
      },
      label = { Text("Digite seu email") },
      isError = !isEmailValid,
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(8.dp),

      colors = TextFieldDefaults.colors(
        focusedIndicatorColor = if (isEmailValid) MaterialTheme.colorScheme.primary else Color.Red,
        unfocusedIndicatorColor = if (isEmailValid) MaterialTheme.colorScheme.onSurface else Color.Red,
        errorIndicatorColor = Color.Red,
        errorLabelColor = Color.Red,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
      )

    )

    if (!isEmailValid) {
      Text(
        text = "Por favor, insira um email válido.",
        color = Color.Red,
        fontSize = 12.sp,
        modifier = Modifier.padding(top = 4.dp)
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Botão de Recuperação
    Button(
      onClick = {
        if (isEmailValid && email.isNotEmpty()) {
          viewModel.resetPassword(email) { success ->
            if (success) {
              Toast.makeText(context, "Email de recuperação enviado!", Toast.LENGTH_LONG).show()
              navController.navigate("login")
            } else {
              Toast.makeText(context, "Erro ao enviar email", Toast.LENGTH_LONG).show()
            }
          }
        } else {
          Toast.makeText(context, "Insira um email válido", Toast.LENGTH_SHORT).show()
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
      shape = RoundedCornerShape(8.dp)
    ) {
      Text("Enviar Email de Recuperação", fontSize = 16.sp)
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Botão para Voltar ao Login
    TextButton(
      onClick = { navController.navigate("login") },
      modifier = Modifier.padding(top = 16.dp)
    ) {
      Text("Voltar ao Login", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
    }
  }
}




