package com.example.iplay.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iplay.ui.theme.ButtonPrimaryColors
import com.example.iplay.ui.theme.ButtonSecondaryColors
import com.example.iplay.ui.theme.PrimaryTextColor

@Composable
fun SettingsScreen(
  navController: NavController,
  isDarkModeEnabled: Boolean,
  areNotificationsEnabled: Boolean,
  onToggleDarkMode: (Boolean) -> Unit,
  onToggleNotifications: (Boolean) -> Unit,
  onClearFavorites: () -> Unit,
  onResetPreferences: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(5.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Opção de Modo Escuro
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Modo Escuro",
        style = MaterialTheme.typography.titleMedium,
        color = PrimaryTextColor,
        modifier = Modifier.weight(2f)
      )
      Switch(
        checked = isDarkModeEnabled,
        onCheckedChange = onToggleDarkMode
      )
    }

    Divider()

    // Opção de Notificações
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Notificações",
        style = MaterialTheme.typography.titleMedium,
        color = PrimaryTextColor,
        modifier = Modifier.weight(2f)
      )
      Switch(
        checked = areNotificationsEnabled,
        onCheckedChange = onToggleNotifications
      )
    }

    Divider()

    // Botão para limpar favoritos
    Button(
      onClick = onClearFavorites,
      modifier = Modifier.fillMaxWidth(),
      colors = ButtonPrimaryColors
    ) {
      Text("Limpar Favoritos")
    }

    // Botão para redefinir preferências
    OutlinedButton(
      onClick = onResetPreferences,
      modifier = Modifier.fillMaxWidth(),
      colors = ButtonSecondaryColors
    ) {
      Text("Redefinir Preferências")
    }
  }
}

