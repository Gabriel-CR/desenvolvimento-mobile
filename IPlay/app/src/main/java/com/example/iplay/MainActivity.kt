package com.example.iplay

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iplay.ui.theme.IPlayTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      IPlayTheme {
        IPlay()
      }
    }
  }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun IPlay() {
  val imageRes = R.drawable.logo
  val context = LocalContext.current

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("") },
        actions = {
          ThreeDotsMenu()
        }
      )
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Logo",
        modifier = Modifier.size(200.dp)
      )

      Spacer(modifier = Modifier.height(16.dp))

      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
      ) {
        items(10) { index ->
          ElevatedCard()

          Spacer(modifier = Modifier.height(5.dp))
        }
      }
    }
  }
}

fun showCustomToast(
  context: Context,
  message: String,
  backgroundColor: Int = Color.BLACK,
  textColor: Int = Color.WHITE,
  padding: Int = 16,
  duration: Int = Toast.LENGTH_LONG
) {
  val toast = Toast(context)
  val view = TextView(context).apply {
    text = message
    setPadding(padding, padding, padding, padding)
    setBackgroundColor(backgroundColor)
    setTextColor(textColor)
  }
  toast.view = view
  toast.duration = duration
  toast.show()
}
