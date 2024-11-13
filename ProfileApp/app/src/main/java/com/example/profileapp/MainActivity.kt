package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)

    val profileImage = findViewById<ImageView>(R.id.profileImage)
    val nameText = findViewById<TextView>(R.id.nameText)
    val descriptionText = findViewById<TextView>(R.id.descriptionText)
    val currentJobText = findViewById<TextView>(R.id.currentJobText)
    val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)

    nameText.text = "Gabriel Castro"
    descriptionText.text = "Estudante de Ciência da Computação"
    currentJobText.text = "Emprego atual: estagiário Ruby on Rails na V(Dev)"


    val experiencias = listOf(
      "Bolsista PID - Universidade Federal do Ceará",
      "Bolsista PIBIC - Universidade Federal do Ceará",
      "Estagiário - V(Dev)"
    )

    for (experiencia in experiencias) {
      val textView = TextView(this)
      textView.text = experiencia
      textView.textSize = 16f
      experienceLayout.addView(textView)
    }

    profileImage.setOnClickListener {
      Toast.makeText(this, "Foto de perfil de ${nameText.text}", Toast.LENGTH_SHORT).show()
    }
  }
}