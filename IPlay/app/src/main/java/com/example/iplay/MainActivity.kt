package com.example.iplay

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iplay.database.SettingsDataStore
import com.example.iplay.factory.AuthViewModelFactory
import com.example.iplay.factory.SettingsViewModelFactory
import com.example.iplay.models.AuthViewModel
import com.example.iplay.models.GameViewModel
import com.example.iplay.models.SettingsViewModel
import com.example.iplay.models.UserViewModel
import com.example.iplay.repository.AuthRepository
import com.example.iplay.ui.components.BottomBar
import com.example.iplay.ui.components.TopBar
import com.example.iplay.ui.screens.FavouritesScreen
import com.example.iplay.ui.screens.GameDetailsScreen
import com.example.iplay.ui.screens.GamesScreen
import com.example.iplay.ui.screens.HelpScreen
import com.example.iplay.ui.screens.HomeScreen
import com.example.iplay.ui.screens.LoginScreen
import com.example.iplay.ui.screens.LogoutScreen
import com.example.iplay.ui.screens.ProfileScreen
import com.example.iplay.ui.screens.RegisterScreen
import com.example.iplay.ui.screens.SearchScreen
import com.example.iplay.ui.screens.SettingsScreen
import com.example.iplay.ui.screens.VideoPlayerScreen
import com.example.iplay.ui.theme.IPlayTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val settingsDataStore = SettingsDataStore(applicationContext)

    setContent {
      val navController = rememberNavController()
      val currentBackStackEntry = navController.currentBackStackEntryAsState()
      val currentRoute = currentBackStackEntry.value?.destination?.route
      val isDarkTheme = isSystemInDarkTheme()
      val gameViewModel: GameViewModel = viewModel()
      val user: UserViewModel = viewModel()
      val repository = AuthRepository()
      val authViewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(AuthViewModel::class.java)
      createNotificationChannel(LocalContext.current)

      val settingVm: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(settingsDataStore)
      )

      val isDarkModeEnabled by settingVm.computedDarkMode.collectAsState()
      val isAutoDarkModeEnabled by settingVm.isAutoDarkModeEnabled.collectAsState()
      val areNotificationsEnabled by settingVm.areNotificationsEnabled.collectAsState()

      IPlayTheme(darkTheme = isDarkModeEnabled) {
        Scaffold(
          topBar = {
            if (currentRoute != "login" && currentRoute != "videoPlayer/{videoResId}" && currentRoute != "register") {
              TopBar(navController)
            }
          },
          bottomBar = {
            if (currentRoute != "login" && currentRoute != "videoPlayer/{videoResId}" && currentRoute != "register") {
              BottomBar(navController)
            }
          }
        ) { innerPadding ->
          NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
          ) {
            composable("login") { LoginScreen(navController, authViewModel) }
            composable("register") { RegisterScreen(authViewModel, navController) }
            composable("home") { HomeScreen(navController, gameViewModel) }
            composable("games") { GamesScreen(navController, gameViewModel) }
            composable("favorites") { FavouritesScreen(navController, gameViewModel) }
            composable("profile") { ProfileScreen(navController, user) }
            composable("search") { SearchScreen(navController, gameViewModel) }
            composable("settings") {
              SettingsScreen(
                navController = navController,
                isDarkModeEnabled = isDarkModeEnabled,
                isAutoDarkModeEnabled = isAutoDarkModeEnabled, // Novo estado para modo escuro automático
                areNotificationsEnabled = areNotificationsEnabled,
                onToggleDarkMode = settingVm::toggleDarkMode,
                onToggleAutoDarkMode = settingVm::toggleAutoDarkMode, // Nova função para alternar o modo automático
                onToggleNotifications = settingVm::toggleNotifications,
                onClearFavorites = { gameViewModel.clearFavorite() },
                onResetPreferences = { settingVm.toggleDarkMode(isDarkTheme) }
              )
            }
            composable("help") { HelpScreen(navController) }
            composable("logout") { LogoutScreen(navController, authViewModel) }
            composable("videoPlayer/{videoResId}") { backStackEntry ->
              val videoResId = backStackEntry.arguments?.getString("videoResId")?.toInt() ?: 0
              VideoPlayerScreen(videoResId, navController)
            }
            composable("gameDetails/{gameId}") { backStackEntry ->
              val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull()
              val game = gameViewModel.gamesView.value.find { it.id == gameId }
              if (game != null) {
                GameDetailsScreen(game, gameViewModel, navController)
              }
            }
          }
        }
      }
    }
  }

  private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        "GAME_CHANNEL",
        "Lembretes de Jogos",
        NotificationManager.IMPORTANCE_HIGH
      ).apply {
        description = "Canal para notificações de jogos"
      }
      val notificationManager: NotificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      notificationManager.createNotificationChannel(channel)
    }
  }
}
