package com.example.postapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.postapp.data.models.Post

@Composable
fun PostItem(
  post: Post,
  onEditConfirm: (Post) -> Unit,
  onDeleteConfirm: (Int) -> Unit
) {
  var showDeleteDialog by remember { mutableStateOf(false) }
  var showEditDialog by remember { mutableStateOf(false) }
  var editedTitle by remember { mutableStateOf(post.title) }
  var editedContent by remember { mutableStateOf(post.content) }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp)
      .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
      .padding(16.dp)
  ) {
    Text(
      text = post.title,
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = post.content,
      style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
      horizontalArrangement = Arrangement.Start,
      modifier = Modifier.fillMaxWidth()
    ) {
      Button(
        onClick = { onEditConfirm(post) },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar",
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "Editar")
        }
      }

      Spacer(modifier = Modifier.width(16.dp))

      Button(
        onClick = { showDeleteDialog = true },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Deletar",
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "Deletar")
        }
      }
    }
  }

  // Modal de confirmação para deletar
  if (showDeleteDialog) {
    AlertDialog(
      onDismissRequest = { showDeleteDialog = false },
      title = { Text(text = "Confirmar Deleção") },
      text = { Text(text = "Você tem certeza que deseja deletar este post?") },
      confirmButton = {
        TextButton(onClick = {
          onDeleteConfirm(post.id)
          showDeleteDialog = false
        }) {
          Text(text = "Sim", color = MaterialTheme.colorScheme.error)
        }
      },
      dismissButton = {
        TextButton(onClick = { showDeleteDialog = false }) {
          Text(text = "Não")
        }
      }
    )
  }

  // Modal de edição
  if (showEditDialog) {
    AlertDialog(
      onDismissRequest = { showEditDialog = false },
      title = { Text(text = "Editar Post") },
      text = {
        Column {
          OutlinedTextField(
            value = editedTitle,
            onValueChange = { editedTitle = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
          )
          Spacer(modifier = Modifier.height(8.dp))
          OutlinedTextField(
            value = editedContent,
            onValueChange = { editedContent = it },
            label = { Text("Conteúdo") },
            modifier = Modifier.fillMaxWidth()
          )
        }
      },
      confirmButton = {
        TextButton(onClick = {
          val id = post.id
          val updatedPost = post.copy(id = id, title = editedTitle, content = editedContent)
          onEditConfirm(updatedPost)
          showEditDialog = false
        }) {
          Text(text = "Salvar", color = MaterialTheme.colorScheme.primary)
        }
      },
      dismissButton = {
        TextButton(onClick = { showEditDialog = false }) {
          Text(text = "Cancelar")
        }
      }
    )
  }
}

