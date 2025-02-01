package com.example.investidorapptarefa.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investidorapptarefa.model.Investimento
import com.example.investidorapptarefa.viewmodel.InvestimentosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun InvestidorScreen(viewModel: InvestimentosViewModel) {
  val investimentos by viewModel.investimentos.collectAsState()
  val snackbarHostState = remember { SnackbarHostState() }
  var snackbarMessage by remember { mutableStateOf<String?>(null) }
  var showDialog by remember { mutableStateOf(false) }

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    topBar = {
      SmallTopAppBar(
        title = {
          Text(
            text = "Investidor App",
            style = MaterialTheme.typography.titleLarge
          )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.primary,
          titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = MaterialTheme.colorScheme.primary
      ) {
        Icon(Icons.Default.Add, contentDescription = "Adicionar Investimento")
      }
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      ListaInvestimentos(investimentos)

      snackbarMessage?.let { message ->
        LaunchedEffect(message) {
          snackbarHostState.showSnackbar(message)
          snackbarMessage = null
        }
      }
    }

    if (showDialog) {
      CriarInvestimentoDialog(
        onDismiss = { showDialog = false },
        onSave = { nome, valor ->
          viewModel.adicionarInvestimento(Investimento(nome, valor))
          showDialog = false
        }
      )
    }
  }
}

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

@Composable
fun ListaInvestimentos(investimentos: List<Investimento>) {
  if (investimentos.isEmpty()) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "Nenhum investimento encontrado.",
        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
      )
    }
  } else {
    LazyColumn(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
      items(investimentos) { investimento ->
        InvestimentoItem(investimento)
      }
    }
  }
}


@Composable
fun InvestimentoItem(investimento: Investimento) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(8.dp)
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Default.ShoppingCart,
        contentDescription = "Ícone de investimento",
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(40.dp)
      )

      Spacer(modifier = Modifier.width(16.dp))

      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = investimento.nome,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Valor: R$ ${investimento.valor}",
          style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
      }
    }
  }
}
