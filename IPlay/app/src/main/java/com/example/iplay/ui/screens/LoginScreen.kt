package com.example.iplay.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iplay.models.AuthViewModel
import com.example.iplay.ui.components.CustomTextField
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

@Composable
fun LoginScreen(
  navController: NavController,
  viewModel: AuthViewModel
) {
  val context = LocalContext.current
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var isVisible by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    isVisible = true
  }

  // Launcher para Google Sign-In
  val googleSignInLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult()
  ) { result ->
    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
    try {
      val account = task.getResult(ApiException::class.java)
      account?.idToken?.let { idToken ->
        viewModel.loginWithGoogle(idToken) { success ->
          if (success) {
            navController.navigate("home")
          } else {
            Toast.makeText(context, "Erro ao fazer login com Google", Toast.LENGTH_SHORT).show()
          }
        }
      }
    } catch (e: ApiException) {
      Toast.makeText(context, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
    }
  }

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    AnimatedVisibility(
      visible = isVisible,
      enter = slideInVertically() + fadeIn()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "I Play!",
          fontSize = 32.sp,
          style = MaterialTheme.typography.headlineLarge,
          modifier = Modifier.padding(bottom = 24.dp),
          color = MaterialTheme.colorScheme.primary
        )

        CustomTextField(
          value = email,
          onValueChange = { email = it },
          label = "Email",
          icon = Icons.Filled.Email,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField(
          value = password,
          onValueChange = { password = it },
          label = "Senha",
          icon = Icons.Filled.Lock,
          isPassword = true,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
          onClick = {
            viewModel.login(email, password) { success ->
              if (success) {
                navController.navigate("home")
              } else {
                Toast.makeText(context, "Usuário ou senha inválida", Toast.LENGTH_SHORT).show()
              }
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
          Text("Entrar", fontSize = 18.sp, color = MaterialTheme.colorScheme.onSecondary)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
          onClick = {
            val signInIntent = viewModel.getGoogleSignInClient(context).signInIntent
            googleSignInLauncher.launch(signInIntent)
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
          Text("Entrar com Google", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(onClick = { navController.navigate("register") }) {
          Text("Criar Conta", fontSize = 14.sp)
        }
      }
    }
  }
}
