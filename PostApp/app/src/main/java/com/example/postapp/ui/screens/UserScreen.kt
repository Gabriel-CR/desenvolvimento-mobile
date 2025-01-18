package com.example.postapp.ui.screens

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapp.viewmodel.PostViewModel

@Composable
fun UserScreen(viewModel: PostViewModel = viewModel()) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var isLoading by remember { mutableStateOf(false) }
  var context = LocalContext.current

  LounchedEffect(Unit) {
    isLoading = true
    viewModel.fetchUsers()
    isLoading = false
  }

  Column(modifier = Modifier.padding(16.dp)) {
    TextField(
      value = name,
      onValueChange = { name = it },
      label = { Text("Nome") },
      modifier = Modifier.fillMaxWidth()
    )

    Space(modifier = Modifier.height(8.dp))

    TextField(
      value = name,
      onValueChange = { name = it },
      label = { Text("Nome") },
      modifier = Modifier.fillMaxWidth()
    )

    Space(modifier = Modifier.height(8.dp))

    Button(onClick = {
      isLoading = true
      PostViewModel
    }) { }
  }
}