package com.example.iplay.models

data class Game(
  val id: Int,
  val name: String,                // Nome do jogo/evento
  val sport: String,               // Esporte do jogo (ex.: "Futebol", "Vôlei", "Tênis")
  val date: String,                // Data do jogo (ex.: "2024-12-20")
  val time: String,                // Horário do jogo (ex.: "18:00")
  val location: String,            // Local do jogo (ex.: "Quadra Central")
  val players: List<String>,       // Lista de jogadores/participantes
  val organizer: String,           // Nome do organizador
  val description: String,         // Descrição adicional (ex.: "Amistoso entre amigos")
  val imageRes: Int,               // ID do recurso da imagem associada ao esporte
  var isFavorite: Boolean = false  // Indica se o jogo foi marcado como favorito
)

