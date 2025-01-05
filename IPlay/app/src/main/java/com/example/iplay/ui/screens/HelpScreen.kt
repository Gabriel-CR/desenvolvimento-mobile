package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.iplay.ui.components.HelpItem

@Composable
fun HelpScreen(
  navController: NavHostController
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Text(
      text = "Ajuda",
      style = MaterialTheme.typography.headlineLarge,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      item {
        HelpItem(
          title = "Como criar um jogo?",
          description = "Para criar um jogo, clique no botão 'Jogos', escolha o esporte, data, horário e local, e convide seus amigos!"
        )
      }
      item {
        HelpItem(
          title = "Como participar de um jogo?",
          description = "Navegue pela lista de jogos disponíveis e clique em 'Participar' para confirmar sua presença."
        )
      }
      item {
        HelpItem(
          title = "Como adicionar favoritos?",
          description = "Clique no ícone de estrela em qualquer jogo para marcá-lo como favorito e acessá-lo facilmente depois."
        )
      }
      item {
        HelpItem(
          title = "Preciso de mais ajuda!",
          description = "Entre em contato com nossa equipe de suporte pelo e-mail suporte@appjogos.com ou acesse nosso FAQ."
        )
      }
    }
  }
}
