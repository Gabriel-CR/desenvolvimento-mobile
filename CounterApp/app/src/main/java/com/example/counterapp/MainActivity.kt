package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.CounterAppTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CounterAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          CounterApp()
        }
      }
    }
  }
}

@Composable
fun CounterApp() {
  var result by remember { mutableDoubleStateOf(0.0) }
  var input by remember { mutableStateOf("") }
  var history by remember { mutableStateOf(listOf<String>()) }

  Column (
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center
    ) {
    LazyColumn {
      items(history.takeLast(5)) { item ->
        Text(text = item)
      }
    }
    
    Text(
      text = "Resultado: $result",
      fontSize = 24.sp,
      modifier = Modifier.padding(8.dp),
      color = Color.Black
    )

    OutlinedTextField(
      value = input,
      onValueChange = { input = it},
      label = { Text("Digite um número") },
      keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), // mostra somente os números do teclado
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Button(
        onClick = {
          result += input.toDoubleOrNull() ?: 0.0
          input = ""
          history = history + "Incrementar $input -> Resultado: $result"
        },
        modifier = Modifier.weight(1f)
      ) {
        Text("+ Incrementar")
      }

      Button(
        onClick = {
          result -= input.toDoubleOrNull() ?: 0.0
          input = ""
          history = history + "Decrementar $input -> Resultado: $result"
        },
        modifier = Modifier.weight(1f)
      ) {
        Text("- Decrementar")
      }
    }

    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)
    ) {
      Button(
        onClick = {
          result *= input.toDoubleOrNull() ?: 0.0
          input = ""
          history = history + "Multiplicar $input -> Resultado: $result"
        },
        modifier = Modifier.weight(1f)
      ) {
        Text("x Multiplicar")
      }

      Button(
        onClick = {
          val value = input.toDoubleOrNull() ?: 1.0
          if (value != 0.0) {
            result /= value
          }
          input = ""
          history = history + "Dividir $input -> Resultado: $result"
        },
        modifier = Modifier.weight(1f)
      ) {
        Text("/ Dividir")
      }
    }

    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(top = 8.dp)
    ) {
      Button(
        onClick = {
          result *= (input.toDoubleOrNull() ?: 0.0) / 100
          input = ""
          history = history + "Porcentagem $input -> Resultado: $result"
        },
        modifier = Modifier.weight(1f)
      ) {
        Text("% Porcentagem")
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = {
        result = 0.0
        input = ""
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = "Limpar"
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text("Limpar")
    }
  }
}