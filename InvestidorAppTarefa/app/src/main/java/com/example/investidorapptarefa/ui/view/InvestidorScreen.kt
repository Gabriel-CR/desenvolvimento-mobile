package com.example.investidorapptarefa.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.investidorapptarefa.model.Investimento
import com.example.investidorapptarefa.ui.components.CriarInvestimentoDialog
import com.example.investidorapptarefa.ui.components.ListaInvestimentos
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
