package com.example.iplay.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iplay.database.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalTime

class SettingsViewModel(private val dataStore: SettingsDataStore) : ViewModel() {

  val isDarkModeEnabled = dataStore.isDarkModeEnabled
    .stateIn(viewModelScope, SharingStarted.Lazily, false)

  val isAutoDarkModeEnabled = dataStore.isAutoDarkModeEnabled
    .stateIn(viewModelScope, SharingStarted.Lazily, false)

  val areNotificationsEnabled = dataStore.areNotificationsEnabled
    .stateIn(viewModelScope, SharingStarted.Lazily, true)

  fun toggleDarkMode(enabled: Boolean) {
    viewModelScope.launch {
      dataStore.toggleDarkMode(enabled)
    }
  }

  fun toggleAutoDarkMode(enabled: Boolean) {
    viewModelScope.launch {
      dataStore.toggleAutoDarkMode(enabled)
    }
  }

  fun toggleNotifications(enabled: Boolean) {
    viewModelScope.launch {
      dataStore.toggleNotifications(enabled)
    }
  }

  // Decide se o modo escuro deve ser ativado automaticamente após as 18h
  val computedDarkMode: StateFlow<Boolean> = combine(isDarkModeEnabled, isAutoDarkModeEnabled) { darkMode, autoMode ->
    if (autoMode) {
      val currentHour = LocalTime.now().hour
      currentHour >= 18 // Ativa modo escuro automaticamente após 18h
    } else {
      darkMode
    }
  }.stateIn(viewModelScope, SharingStarted.Lazily, false)
}

