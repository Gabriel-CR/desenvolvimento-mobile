package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.R
import com.example.iplay.models.Game
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.DatePickerField
import com.example.iplay.ui.components.TimePickerField
import com.example.iplay.ui.theme.ButtonPrimaryColors
import com.example.iplay.ui.theme.IconButtonTransparentBorder

@Composable
fun GamesScreen(
  navController: NavController,
  viewModel: GameViewModel
) {
  var gameName by remember { mutableStateOf("") }
  var sport by remember { mutableStateOf("") }
  var date by remember { mutableStateOf("") }
  var time by remember { mutableStateOf("") }
  var location by remember { mutableStateOf("") }
  var playerName by remember { mutableStateOf("") }
  var players by remember { mutableStateOf<List<String>>(emptyList()) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Text(
      text = "Criar Jogo",
      style = MaterialTheme.typography.headlineLarge
    )

    OutlinedTextField(
      value = gameName,
      onValueChange = { gameName = it },
      label = { Text("Nome do Jogo") },
      modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
      value = sport,
      onValueChange = { sport = it },
      label = { Text("Esporte") },
      modifier = Modifier.fillMaxWidth()
    )

    DatePickerField(
      selectedDate = date,
      onDateSelected = { selectedDate ->
        date = selectedDate
      }
    )

    TimePickerField(
      selectedTime = time,
      onTimeSelected = { selectedTime ->
        time = selectedTime
      }
    )

    OutlinedTextField(
      value = location,
      onValueChange = { location = it },
      label = { Text("Local") },
      modifier = Modifier.fillMaxWidth()
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutlinedTextField(
        value = playerName,
        onValueChange = { playerName = it },
        label = { Text("Nome do Jogador") },
        modifier = Modifier.weight(1f)
      )

      IconButton(
        onClick = {
          if (playerName.isNotBlank()) {
            players = players + playerName
            playerName = ""
          }
        },
        colors = IconButtonTransparentBorder
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Adicionar Jogador"
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = {
        val newGame = Game(
          id = viewModel.getGamesSize() + 1,
          name = gameName,
          sport = sport,
          date = date,
          time = time,
          location = location,
          players = players,
          organizer = "Usuário Atual",
          description = "Novo jogo criado pelo usuário.",
          imageRes = R.drawable.logo,
          isFavorite = false
        )
        viewModel.addGame(newGame)
        navController.navigate("home")
      },
      modifier = Modifier.align(Alignment.CenterHorizontally),
      colors = ButtonPrimaryColors
    ) {
      Text("Criar Jogo")
    }
  }
}
