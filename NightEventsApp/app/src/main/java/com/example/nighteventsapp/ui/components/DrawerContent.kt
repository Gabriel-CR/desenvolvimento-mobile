package com.example.nighteventsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.Modifier
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(navController: NavHostController, onSendNotification: () -> Unit) {
  Surface(
    modifier = Modifier.fillMaxHeight()
      .width(380.dp)
      .padding(end = 20.dp)
      .background(MaterialTheme.colorScheme.surface),
    color = MaterialTheme.colorScheme.surface
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      Text(
        text = "Menu",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(bottom = 16.dp)
      )

      Divider()
      Spacer(modifier = Modifier.height(16.dp))

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { navController.navigate("profile") }
          .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Person,
          contentDescription = "Perfil",
          modifier = Modifier.size(24.dp),
          tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
          text = "Perfil",
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onSendNotification() }
          .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Notifications,
          contentDescription = "Notificação",
          modifier = Modifier.size(24.dp),
          tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
          text = "Notificação",
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable {  }
          .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Logout,
          contentDescription = "Sair",
          modifier = Modifier.size(24.dp),
          tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
          text = "Sair",
          color = MaterialTheme.colorScheme.error,
          modifier = Modifier
            .clickable {}
            .padding(vertical = 8.dp)
        )
      }

      Spacer(modifier = Modifier.weight(1f))

      Text(
        text = "Versão 0.0.0.1",
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp)
      )
    }
  }
}
