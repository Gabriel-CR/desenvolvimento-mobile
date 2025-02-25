package com.example.iplay.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.iplay.ui.screens.screens
import com.example.iplay.ui.theme.NavigationBarColors

@Composable
fun BottomBar(
  navController : NavController
) {
  val currentDestination = navController.currentBackStackEntryAsState().value?.destination

  NavigationBar {
    screens.forEach { screen ->
      NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
          navController.navigate(screen.route) {
            popUpTo(navController.graph.startDestinationId) { saveState = true }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = screen.icon,
        label = {
          Text(screen.label)
        },
        colors = NavigationBarColors
      )
    }
  }
}
