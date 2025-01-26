package com.example.iplay.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iplay.database.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val dataStore: SettingsDataStore) : ViewModel() {

  val isDarkModeEnabled = dataStore.isDarkModeEnabled
    .stateIn(viewModelScope, SharingStarted.Lazily, false)

  val areNotificationsEnabled = dataStore.areNotificationsEnabled
    .stateIn(viewModelScope, SharingStarted.Lazily, true)

  fun toggleDarkMode(enabled: Boolean) {
    viewModelScope.launch {
      dataStore.toggleDarkMode(enabled)
    }
  }

  fun toggleNotifications(enabled: Boolean) {
    viewModelScope.launch {
      dataStore.toggleNotifications(enabled)
    }
  }
}
