package com.example.msgapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageBubble(content: String, isUserMessage: Boolean) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
  ) {
    Box(
      modifier = Modifier
        .background(
          color = if (isUserMessage) {
            MaterialTheme.colorScheme.primary
          } else {
            MaterialTheme.colorScheme.surface
          },
          shape = RoundedCornerShape(
            topStart = if (isUserMessage) 16.dp else 0.dp,
            topEnd = if (isUserMessage) 0.dp else 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
          )
        )
        .padding(12.dp)
        .widthIn(max = 250.dp)
        .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
      Text(
        text = content,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = if (isUserMessage) {
            MaterialTheme.colorScheme.onPrimary
          } else {
            MaterialTheme.colorScheme.onSurface
          }
        )
      )
    }
  }
}
