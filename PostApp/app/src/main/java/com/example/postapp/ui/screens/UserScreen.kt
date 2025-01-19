package com.example.postapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapp.ui.components.UserItem
import com.example.postapp.viewmodel.PostViewModel

@Composable
fun UserScreen(viewModel: PostViewModel = viewModel()) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var isLoading by remember { mutableStateOf(false) }
  val context = LocalContext.current

  // Atualiza a lista de usuários ao carregar a tela
  LaunchedEffect(Unit) {
    isLoading = true
    viewModel.fetchUsers()
    isLoading = false
  }

  Column(modifier = Modifier.padding(16.dp)) {
    Text(
      text = "Criar Usuário",
      style = MaterialTheme.typography.headlineSmall,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    // Campo para Nome
    TextField(
      value = name,
      onValueChange = { name = it },
      label = { Text("Nome") },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Campo para E-mail
    TextField(
      value = email,
      onValueChange = { email = it },
      label = { Text("E-mail") },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Botão de Criar Usuário
    Button(
      onClick = {
        if (name.isNotBlank() && email.isNotBlank()) {
          isLoading = true
          viewModel.createUser(
            name = name,
            email = email,
            onSuccess = {
              Toast.makeText(context, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
              name = ""
              email = ""
              isLoading = false
            },
            onError = {
              Toast.makeText(context, "Erro ao criar usuário.", Toast.LENGTH_SHORT).show()
              isLoading = false
            }
          )
        } else {
          Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
      },
      modifier = Modifier.fillMaxWidth(),
      enabled = !isLoading
    ) {
      Text(text = if (isLoading) "Carregando..." else "Criar Usuário")
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Lista de Usuários
    Text(
      text = "Lista de Usuários",
      style = MaterialTheme.typography.headlineSmall,
      modifier = Modifier.padding(bottom = 8.dp)
    )

    if (isLoading) {
      CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    } else {
      LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(viewModel.users) { user ->
          UserItem(user = user)
        }
      }
    }
  }
}
