package com.example.postapp.ui.screens

import android.util.Log
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
import androidx.compose.material3.AlertDialog
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
import com.example.postapp.viewmodel.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapp.data.models.Post
import com.example.postapp.ui.components.PostItem

@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()) {
  var title by remember { mutableStateOf("") }
  var content by remember { mutableStateOf("") }
  var isLoading by remember { mutableStateOf(false) }
  var onPostEdit by remember { mutableStateOf<Post?>(null) }
  val context = LocalContext.current

  // Atualiza a lista de posts ao carregar a tela
  LaunchedEffect(Unit) {
    isLoading = true
    viewModel.fetchPosts()
    isLoading = false
  }

  Column(modifier = Modifier.padding(16.dp)) {
    Text(
      text = "Criar Post",
      style = MaterialTheme.typography.headlineSmall,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    // Campo para Título
    TextField(
      value = title,
      onValueChange = { title = it },
      label = { Text("Título") },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Campo para Conteúdo
    TextField(
      value = content,
      onValueChange = { content = it },
      label = { Text("Conteúdo") },
      modifier = Modifier.fillMaxWidth(),
      maxLines = 5
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Botão de Criar Post
    Button(
      onClick = {
        if (title.isNotBlank() && content.isNotBlank()) {
          isLoading = true
          viewModel.createPost(
            title = title,
            content = content,
            onSuccess = {
              Toast.makeText(context, "Post criado com sucesso!", Toast.LENGTH_SHORT).show()
              title = ""
              content = ""
              isLoading = false
            },
            onError = {
              Toast.makeText(context, "Erro ao criar post.", Toast.LENGTH_SHORT).show()
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
      Text(text = if (isLoading) "Carregando..." else "Criar Post")
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Lista de Posts
    Text(
      text = "Lista de Posts",
      style = MaterialTheme.typography.headlineSmall,
      modifier = Modifier.padding(bottom = 8.dp)
    )

    if (isLoading) {
      CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    } else {
      Log.d("PostScreen", "Mostrando ${viewModel.posts.size} posts")
      LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(viewModel.posts) { post ->
          PostItem(
            post = post,
            onEditConfirm = {
              onPostEdit = it
              Log.d("PostItem", "Post atualizado: ${it.title}")
            },
            onDeleteConfirm = {
              viewModel.deletePost(it)
              Log.d("PostItem", "Post deletado")
            }
          )
        }
      }
    }

    if (onPostEdit != null) {
      AlertDialog(
        onDismissRequest = { onPostEdit = null},
        title = { Text(text = "Editar Post")},
        text = {
          Column {
            TextField(value = onPostEdit!!.title,
              onValueChange = {newTitle -> onPostEdit = onPostEdit!!.copy(title = newTitle)},
              label = {Text("Título")}
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = onPostEdit!!.content,
              onValueChange = {newContent -> onPostEdit = onPostEdit!!.copy(content = newContent)},
              label = {Text("Título")}
            )
          }
        },
        confirmButton = {
          Button(onClick = {
            viewModel.updatePost(
              onPostEdit!!.id,
              onPostEdit!!.title,
              onPostEdit!!.content
            )
            onPostEdit = null
          }) {
            Text(text = "Salvar")
          }
        },

        dismissButton = {
          Button(onClick = {
            onPostEdit = null
          }) {
            Text(text = "Cancelar")
          }
        }

      )
    }
  }
}
