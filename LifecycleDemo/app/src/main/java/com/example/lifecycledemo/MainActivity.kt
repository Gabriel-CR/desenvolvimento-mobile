package com.example.lifecycledemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifecycledemo.ui.theme.CounterScreen
import com.example.lifecycledemo.ui.theme.LifecycleDemoTheme
import com.example.lifecycledemo.ui.theme.LoadingScreen

class MainActivity : ComponentActivity() {
  private var isLoading by mutableStateOf(true)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    // Mensagem de log de toast on create
    Log.d("App", "OnCreate chamado")
    Toast.makeText(this, "OnCreate chamado", Toast.LENGTH_SHORT).show()

    // Este método é usado para configurar a interface inicial e inicializar variáveis e componentes
    setContent {
      LifecycleDemoTheme {
        LoadingScreen()
      }
    }
  }

  override fun onStart() {
    super.onStart()
    enableEdgeToEdge()

    Log.d("App", "OnStart Chamado")
    Toast.makeText(this, "OnStart Chamado", Toast.LENGTH_SHORT).show()

    // Aqui, a Activity está em segundo plano, pronta para ser vista
    if (isLoading) {
      setContent {
        LifecycleDemoTheme {
          LoadingScreen()
        }
      }

      Handler(Looper.getMainLooper()).postDelayed({
        isLoading = false
        invalidateContent()
      }, 2000)
    }
  }

  override fun onResume() {
    super.onResume()
    enableEdgeToEdge()

    Log.d("App", "OnResume Chamado")
    Toast.makeText(this, "OnResume Chamado", Toast.LENGTH_SHORT).show()

    // Chamado quando a Activity está no topo da pilha e o usuário pode interagir com ela
    if (!isLoading) {
      setContent {
        LifecycleDemoTheme {
          CounterScreen()
        }
      }
    }

  }

  override fun onPause() {
    super.onPause()
    enableEdgeToEdge()

    // Chamado quando o usuário está saindo da Activity, mas ela ainda está visível
    Log.d("App", "OnPause Chamado")
    Toast.makeText(this, "OnPause Chamado", Toast.LENGTH_SHORT).show()
  }

  override fun onStop() {
    super.onStop()
    enableEdgeToEdge()

    // Chamado quando a Activity não está mais visível, sendo colocada em segundo plano
    Log.d("App", "OnStop Chamado")
    Toast.makeText(this, "OnStop Chamado", Toast.LENGTH_SHORT).show()
  }

  override fun onDestroy() {
    super.onDestroy()
    enableEdgeToEdge()

    // Chamado antes que a Activity seja destruída
    Log.d("App", "OnDestroy Chamado")
    Toast.makeText(this, "OnDestroy Chamado", Toast.LENGTH_SHORT).show()
  }

  private fun invalidateContent() {
    setContent {
      LifecycleDemoTheme {
        if (isLoading) {
          LoadingScreen()
        } else {
          CounterScreen()
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  LifecycleDemoTheme {
    Greeting("Android")
  }
}