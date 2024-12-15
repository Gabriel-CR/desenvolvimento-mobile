package com.example.iplay.models

import com.example.iplay.R

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

val games = listOf(
  Game(
    id = 1,
    name = "Amistoso no Parque",
    sport = "Futebol",
    date = "2024-12-20",
    time = "18:00",
    location = "Campo do Parque",
    players = listOf("João", "Maria", "Carlos", "Ana"),
    organizer = "Pedro",
    description = "Jogo amistoso para diversão.",
    imageRes = R.drawable.logo,
    isFavorite = false
  ),
  Game(
    id = 2,
    name = "Partida na Praia",
    sport = "Vôlei",
    date = "2024-12-22",
    time = "15:00",
    location = "Praia Central",
    players = listOf("Fernanda", "Lucas", "Rafael", "Juliana"),
    organizer = "Marcos",
    description = "Partida na areia com amigos.",
    imageRes = R.drawable.logo,
    isFavorite = true
  ),
  Game(
    id = 3,
    name = "Torneio Local de Tênis",
    sport = "Tênis",
    date = "2024-12-25",
    time = "09:00",
    location = "Clube Esportivo",
    players = listOf("Ricardo", "Beatriz"),
    organizer = "Clube Esportivo",
    description = "Partida oficial do torneio local.",
    imageRes = R.drawable.logo,
    isFavorite = false
  ),
  Game(
    id = 4,
    name = "Treino de Basquete",
    sport = "Basquete",
    date = "2024-12-19",
    time = "20:00",
    location = "Ginásio Municipal",
    players = listOf("Eduardo", "Paula", "Felipe", "Camila"),
    organizer = "André",
    description = "Jogo treino para o campeonato.",
    imageRes = R.drawable.logo,
    isFavorite = false
  ),
  Game(
    id = 5,
    name = "Partida na Escola",
    sport = "Futebol",
    date = "2024-12-24",
    time = "17:00",
    location = "Campo da Escola",
    players = listOf("Gustavo", "Larissa", "Rafael", "Carla"),
    organizer = "Tiago",
    description = "Partida entre amigos.",
    imageRes = R.drawable.logo,
    isFavorite = true
  ),
  Game(
    id = 6,
    name = "Torneio de Tênis de Mesa",
    sport = "Tênis de Mesa",
    date = "2024-12-23",
    time = "14:00",
    location = "Centro Comunitário",
    players = listOf("Bruno", "Marina"),
    organizer = "Associação de Moradores",
    description = "Torneio de final de ano.",
    imageRes = R.drawable.logo,
    isFavorite = false
  ),
  Game(
    id = 7,
    name = "Partida de Badminton",
    sport = "Badminton",
    date = "2024-12-21",
    time = "11:00",
    location = "Academia Central",
    players = listOf("Leonardo", "Tatiana"),
    organizer = "Equipe Local",
    description = "Partida amistosa.",
    imageRes = R.drawable.logo,
    isFavorite = false
  ),
  Game(
    id = 8,
    name = "Treino de Futebol Americano",
    sport = "Futebol Americano",
    date = "2024-12-26",
    time = "16:00",
    location = "Campo Central",
    players = listOf("Matheus", "Diego", "Fernanda", "Bruna"),
    organizer = "Time Local",
    description = "Jogo treino para iniciantes.",
    imageRes = R.drawable.logo,
    isFavorite = true
  ),
  Game(
    id = 9,
    name = "Treino de Handebol",
    sport = "Handebol",
    date = "2024-12-27",
    time = "19:00",
    location = "Quadra Municipal",
    players = listOf("Thiago", "Vanessa", "Renato", "Patrícia"),
    organizer = "Equipe Regional",
    description = "Treino para o campeonato estadual.",
    imageRes = R.drawable.logo,
    isFavorite = false
  ),
  Game(
    id = 10,
    name = "Encerramento do Ano",
    sport = "Futebol",
    date = "2024-12-28",
    time = "10:00",
    location = "Campo do Clube",
    players = listOf("Daniel", "Roberta", "Lucas", "Isabela"),
    organizer = "Grupo Amigos do Esporte",
    description = "Jogo para fechar o ano.",
    imageRes = R.drawable.logo,
    isFavorite = true
  )
)

