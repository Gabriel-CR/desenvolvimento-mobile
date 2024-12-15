package com.example.iplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iplay.models.games
import com.example.iplay.ui.components.BottomBar
import com.example.iplay.ui.components.DrawerContent
import com.example.iplay.ui.components.TopBar
import com.example.iplay.ui.screens.ConfigurationScreen
import com.example.iplay.ui.screens.FavouritesScreen
import com.example.iplay.ui.screens.GameDetailsScreen
import com.example.iplay.ui.screens.GamesScreen
import com.example.iplay.ui.screens.HomeScreen
import com.example.iplay.ui.screens.ProfileScreen
import com.example.iplay.ui.theme.IPlayTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      val drawerState = rememberDrawerState(DrawerValue.Closed)
      val isDarkTheme = isSystemInDarkTheme()
      var isDarkThemeManual by remember { mutableStateOf(isDarkTheme) }

      IPlayTheme(darkTheme = isDarkThemeManual) {
        ModalNavigationDrawer(
          drawerState = drawerState,
          gesturesEnabled = true,
          drawerContent = { DrawerContent(navController) },
          content = {
            Scaffold(
              topBar = { TopBar(navController) },
              bottomBar = { BottomBar(navController) }
            ) { innerPadding ->
              NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
              ) {
                composable("home") { HomeScreen(navController) }
                composable("games") { GamesScreen(navController) }
                composable("favorites") { FavouritesScreen(navController) }
                composable("profile") { ProfileScreen(navController) }
                composable("configuration") { ConfigurationScreen(navController) }
                composable(
                  route = "gameDetails/{gameId}",
                  arguments = listOf(navArgument("gameId") { type = NavType.IntType })
                ) { backStackEntry ->
                  val gameId = backStackEntry.arguments?.getInt("gameId")
                  val game = games.find { it.id == gameId }
                  if (game != null) {
                    GameDetailsScreen(game)
                  } else {
                    Text("Jogo não encontrado.")
                  }
                }
              }
            }
          }
        )
      }
    }
  }
}
