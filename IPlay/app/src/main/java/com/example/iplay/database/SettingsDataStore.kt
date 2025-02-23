package com.example.iplay.database

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensão para DataStore
val Context.dataStore by preferencesDataStore(name = "settings")

// Chaves de preferências
object SettingsKeys {
  val DARK_MODE = booleanPreferencesKey("dark_mode")
  val AUTO_DARK_MODE = booleanPreferencesKey("auto_dark_mode") // Nova chave
  val NOTIFICATIONS = booleanPreferencesKey("notifications")
}

// Gerenciamento do DataStore
class SettingsDataStore(private val context: Context) {

  val isDarkModeEnabled: Flow<Boolean> = context.dataStore.data
    .map { preferences ->
      preferences[SettingsKeys.DARK_MODE] ?: false
    }

  val isAutoDarkModeEnabled: Flow<Boolean> = context.dataStore.data
    .map { preferences ->
      preferences[SettingsKeys.AUTO_DARK_MODE] ?: false
    }

  val areNotificationsEnabled: Flow<Boolean> = context.dataStore.data
    .map { preferences ->
      preferences[SettingsKeys.NOTIFICATIONS] ?: true
    }

  suspend fun toggleDarkMode(enabled: Boolean) {
    context.dataStore.edit { preferences ->
      preferences[SettingsKeys.DARK_MODE] = enabled
    }
  }

  suspend fun toggleAutoDarkMode(enabled: Boolean) {
    context.dataStore.edit { preferences ->
      preferences[SettingsKeys.AUTO_DARK_MODE] = enabled
    }
  }

  suspend fun toggleNotifications(enabled: Boolean) {
    context.dataStore.edit { preferences ->
      preferences[SettingsKeys.NOTIFICATIONS] = enabled
    }
  }
}
