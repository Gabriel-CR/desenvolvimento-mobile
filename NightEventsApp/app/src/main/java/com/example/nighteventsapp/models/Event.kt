package com.example.nighteventsapp.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.nighteventsapp.R

data class Event(
  val id: Int,
  val title: String,
  val description: String,
  val date: String,
  val location: String,
  val isFavorite: MutableState<Boolean> = mutableStateOf(false),
  val isSubscribed: MutableState<Boolean> = mutableStateOf(false),
  val imageRes: Int
)

val eventList = listOf(
  Event(
    id = 1,
    title = "Conferência de Tecnologia 2024",
    description = "Tendências em tecnologia.",
    date = "2024-12-15",
    location = "Parque Tecnológico",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img1
  ),
  Event(
    id = 2,
    title = "Workshop de Inteligência Artificial",
    description = "Aprenda sobre IA e machine learning.",
    date = "2024-12-20",
    location = "Centro de Inovação",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img2
  ),
  Event(
    id = 3,
    title = "Feira de Empreendedorismo",
    description = "Oportunidades para startups e networking.",
    date = "2024-12-18",
    location = "Auditório Principal",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img3
  ),
  Event(
    id = 4,
    title = "Hackathon Universitário",
    description = "Resolva desafios tecnológicos em equipe.",
    date = "2024-12-22",
    location = "Campus Universitário",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img4
  ),
  Event(
    id = 5,
    title = "Seminário de Sustentabilidade",
    description = "Discussão sobre práticas sustentáveis.",
    date = "2024-12-10",
    location = "Centro de Convenções",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img5
  ),
  Event(
    id = 6,
    title = "Maratona de Desenvolvimento de Games",
    description = "Criação de jogos em 48 horas.",
    date = "2024-12-17",
    location = "Laboratório de Computação",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img6
  ),
  Event(
    id = 7,
    title = "Expo Tech Future",
    description = "Apresentação de novas tecnologias.",
    date = "2024-12-25",
    location = "Centro de Exposições",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img7
  ),
  Event(
    id = 8,
    title = "Curso de Desenvolvimento Mobile",
    description = "Aprenda a criar apps do zero.",
    date = "2024-12-08",
    location = "Escola Técnica",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img8
  ),
  Event(
    id = 9,
    title = "Palestra: O Futuro da Robótica",
    description = "Desafios e avanços em robótica.",
    date = "2024-12-30",
    location = "Teatro Municipal",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img9
  ),
  Event(
    id = 10,
    title = "Congresso de Segurança da Informação",
    description = "Novas tendências em cibersegurança.",
    date = "2024-12-12",
    location = "Hotel Global",
    isFavorite = mutableStateOf(false),
    isSubscribed = mutableStateOf(false),
    imageRes = R.drawable.img10
  )
)

