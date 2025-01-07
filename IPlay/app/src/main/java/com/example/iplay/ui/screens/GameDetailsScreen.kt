package com.example.iplay.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.R
import com.example.iplay.models.Game
import com.example.iplay.models.GameViewModel
import com.example.iplay.ui.components.VideoCard
import com.example.iplay.ui.theme.ButtonPrimaryColors
import com.example.iplay.ui.theme.CardTransparentBorder
import com.example.iplay.ui.theme.CardTransparentColors

@Composable
fun GameDetailsScreen(
  game: Game,
  gameViewModel: GameViewModel,
  navController: NavController
) {
  var isFavorite by remember { mutableStateOf(game.isFavorite) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(rememberScrollState())
  ) {
    Image(
      painter = painterResource(id = game.imageRes),
      contentDescription = game.name,
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp)),
      contentScale = ContentScale.Crop
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = game.name,
      style = MaterialTheme.typography.titleLarge,
      fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(text = "Esporte: ${game.sport}", style = MaterialTheme.typography.bodyMedium)
    Text(text = "Data: ${game.date}", style = MaterialTheme.typography.bodyMedium)
    Text(text = "Hora: ${game.time}", style = MaterialTheme.typography.bodyMedium)
    Text(text = "Local: ${game.location}", style = MaterialTheme.typography.bodyMedium)
    Text(text = "Organizador: ${game.organizer}", style = MaterialTheme.typography.bodyMedium)

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Descrição:",
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold
    )
    Text(text = game.description, style = MaterialTheme.typography.bodyMedium)

    Spacer(modifier = Modifier.height(16.dp))

    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .border(CardTransparentBorder, shape = MaterialTheme.shapes.medium),
      elevation = CardDefaults.cardElevation(8.dp)
    ) {
      Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Button(
          onClick = {
            gameViewModel.toggleFavorite(game.id)
            isFavorite = !isFavorite
          },
          modifier = Modifier.fillMaxWidth(),
          colors = ButtonPrimaryColors
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(if (isFavorite) "Remover Favoritos" else "Adicionar Favoritos")
        }

        Text(
          text = "Vídeos:",
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold
        )
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          val videos = listOf(
            R.raw.basquete,
            R.raw.tenis,
            R.raw.futebol
          )

          items(videos) { videoResId ->
            VideoCard(videoResId = videoResId, navController = navController, context = LocalContext.current)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Jogadores:",
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold
    )
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(game.players) { player ->
        Card(
          modifier = Modifier
            .size(100.dp)
            .padding(4.dp),
          elevation = CardDefaults.cardElevation(4.dp)
        ) {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
          ) {
            Text(
              text = player,
              style = MaterialTheme.typography.bodySmall,
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }
  }
}
