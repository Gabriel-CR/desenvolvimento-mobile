package com.example.iplay

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("NotConstructor")
@Composable
fun ButtonMenu(text: String, context: Context, onClick: () -> Unit) {
  Button(onClick = onClick, modifier = Modifier.fillMaxWidth(0.5f)) {
    Text(text)
  }
}
