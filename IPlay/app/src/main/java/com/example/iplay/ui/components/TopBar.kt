package com.example.iplay.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
  navController: NavController
) {
  TopAppBar(
    title = {
      Row(verticalAlignment = Alignment.Top) {
        Image(
          painter = painterResource(id = R.drawable.logo),
          contentDescription = "App logo",
          modifier = Modifier.size(30.dp).padding(end = 8.dp)
        )

        Text(
          text = "I Play",
          style = MaterialTheme.typography.titleLarge,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
    },
    actions = {
      ThreeDotsMenu(navController)
    }
  )
}