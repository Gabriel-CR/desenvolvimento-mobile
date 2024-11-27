package com.example.zooapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zooapp.ui.screens.CampoFutebolScreen
import com.example.zooapp.ui.screens.HomeScreen
import com.example.zooapp.models.camposFutebol

@ExperimentalMaterial3Api
@Composable
fun NavGraph() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "home") {
    composable("home") {
      HomeScreen(onCampoFutebolSelected = { campoFutebol ->
        navController.navigate("campo/${campoFutebol.name}")
      })
    }
    composable("campo/{campo}") { backStackEntry ->
      val campoFutebolName = backStackEntry.arguments?.getString("campo")
      val selectedCampoFutebol = camposFutebol.first { it.name == campoFutebolName }
      CampoFutebolScreen(selectedCampoFutebol)
    }
  }
}
