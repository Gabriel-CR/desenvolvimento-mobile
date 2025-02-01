package com.example.investidorapptarefa.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CriarInvestimentoDialog(onDismiss: () -> Unit, onSave: (String, Int) -> Unit) {
  var nome by remember { mutableStateOf("") }
  var valor by remember { mutableStateOf("") }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Novo Investimento") },
    text = {
      Column {
        OutlinedTextField(
          value = nome,
          onValueChange = { nome = it },
          label = { Text("Nome do Investimento") },
          singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
          value = valor,
          onValueChange = { valor = it.filter { char -> char.isDigit() } },
          label = { Text("Valor (R$)") },
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (nome.isNotBlank() && valor.isNotBlank()) {
            onSave(nome, valor.toInt())
          }
        }
      ) {
        Text("Salvar")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancelar")
      }
    }
  )
}