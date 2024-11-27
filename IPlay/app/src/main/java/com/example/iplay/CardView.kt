package com.example.iplay

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedCard() {
  val context = LocalContext.current

  ElevatedCard(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 6.dp
    ),
    modifier = Modifier.fillMaxWidth()
  ) {
    Text(
      text = "Elevated",
      modifier = Modifier
        .padding(16.dp),
      textAlign = TextAlign.Center,
    )

    ButtonMenu(
      text = "Item 3",
      context = context,
      onClick = {
        showCustomToast(
          context = context,
          message = "Item 3"
        )
      }
    )
  }
}
