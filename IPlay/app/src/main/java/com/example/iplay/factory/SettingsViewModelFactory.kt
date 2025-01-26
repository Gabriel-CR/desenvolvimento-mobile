package com.example.iplay.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iplay.database.SettingsDataStore
import com.example.iplay.models.SettingsViewModel

class SettingsViewModelFactory(
  private val settingsDataStore: SettingsDataStore
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return SettingsViewModel(settingsDataStore) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}
