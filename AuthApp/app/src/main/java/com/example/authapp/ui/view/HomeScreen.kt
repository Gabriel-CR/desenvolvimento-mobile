package com.example.authapp.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.authapp.viewmodel.AuthViewModel

@Composable
fun HomeScreen(viewModel: AuthViewModel, navController: NavController) {
  var userName by remember { mutableStateOf("Carregando...") }
  var userProfilePicture by remember { mutableStateOf<String?>(null) }
  var isVisible by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    viewModel.getUserName { name ->
      userName = name ?: "Usuário"
    }
    viewModel.getUserProfilePicture { photoUrl ->
      userProfilePicture = photoUrl
    }
    isVisible = true
  }

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    AnimatedVisibility(
      visible = isVisible,
      enter = slideInVertically(initialOffsetY = { -50 }) + fadeIn()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Exibe a foto do usuário, se disponível
        userProfilePicture?.let { url ->
          AsyncImage(
            model = url,
            contentDescription = "Foto de perfil",
            modifier = Modifier
              .size(100.dp)
              .clip(CircleShape)
              .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentScale = ContentScale.Crop
          )
        }

        Text(
          text = "Bem-vindo, $userName!",
          fontSize = 28.sp,
          style = MaterialTheme.typography.headlineMedium,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )

        Button(
          onClick = {
            viewModel.logout()
            navController.navigate("login")
          },
          modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(50.dp),
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
          Text("Sair", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
        }
      }
    }
  }
}
