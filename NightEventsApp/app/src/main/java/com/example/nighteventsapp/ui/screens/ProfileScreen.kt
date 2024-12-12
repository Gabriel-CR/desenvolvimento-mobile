package com.example.nighteventsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.nighteventsapp.R
import com.example.nighteventsapp.ui.components.EditableTextField

@Composable
fun ProfileScreen(navController: NavHostController) {
  var name by remember { mutableStateOf("John Doe") }
  var email by remember { mutableStateOf("johndoe@example.com") }
  var isEditing by remember { mutableStateOf(false) }

  Surface(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    color = MaterialTheme.colorScheme.background
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .size(120.dp)
          .clip(CircleShape)
          .background(MaterialTheme.colorScheme.surface)
      ) {
        Image(
          painter = painterResource(R.drawable.img1),
          contentDescription = "User Avatar",
          modifier = Modifier.fillMaxSize()
        )
      }
      Spacer(modifier = Modifier.height(16.dp))

      if (isEditing) {
        EditableTextField(
          value = name,
          onValueChange = { name = it }
        )
      } else {
        Text(
          text = name,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Center
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      if (isEditing) {
        EditableTextField(
          value = email,
          onValueChange = { email = it }
        )
      } else {
        Text(
          text = email,
          fontSize = 16.sp,
          color = Color.Gray,
          textAlign = TextAlign.Center
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      Button(
        onClick = { isEditing = !isEditing },
        modifier = Modifier.padding(horizontal = 32.dp)
      ) {
        Text(if (isEditing) "Save Changes" else "Edit Profile")
      }
    }
  }
}
