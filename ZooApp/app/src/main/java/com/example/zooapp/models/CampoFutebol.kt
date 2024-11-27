package com.example.zooapp.models

import com.example.zooapp.R

data class CampoFutebol(
  val id: Int,
  val name: String,
  val imageRes: Int,
  val location: String,
  val type: String,
  val dimensions: String,
  val capacity: Int,
  val hasLighting: Boolean,
  val hasChangingRooms: Boolean,
  val hasParking: Boolean,
  val surfaceCondition: String,
  val roofed: Boolean,
  val availability: String,
  val pricePerHour: Double
)

val camposFutebol = listOf(
  CampoFutebol(1, "Arena da Bola", R.drawable.campo_img,"Rua das Palmeiras, 120 - São Paulo", "gramado natural", "100m x 60m", 22, true, true, true, "excelente", false, "08:00-22:00", 150.0),
  CampoFutebol(2, "Gol de Placa", R.drawable.campo_img2, "Av. Brasil, 234 - Rio de Janeiro", "gramado sintético", "90m x 50m", 18, true, true, false, "bom", false, "09:00-20:00", 120.0),
  CampoFutebol(3, "Arena Society", R.drawable.campo_img, "Rua João Paulo II, 400 - Curitiba", "gramado sintético", "80m x 40m", 14, true, true, true, "excelente", false, "07:00-23:00", 100.0),
  CampoFutebol(4, "Campo Verde", R.drawable.campo_img2, "Estrada do Campo, 76 - Porto Alegre", "gramado natural", "110m x 70m", 22, false, false, true, "regular", false, "08:00-18:00", 90.0),
  CampoFutebol(5, "Bola na Rede", R.drawable.campo_img, "Praça dos Atletas, 12 - Salvador", "gramado sintético", "100m x 50m", 20, true, true, true, "bom", false, "10:00-22:00", 140.0),
  CampoFutebol(6, "FutPark", R.drawable.campo_img2, "Rua do Esporte, 98 - Belo Horizonte", "gramado sintético", "90m x 45m", 18, true, false, true, "ótimo", false, "08:00-20:00", 110.0),
  CampoFutebol(7, "Esporte Clube", R.drawable.campo_img, "Av. Central, 678 - Recife", "areia", "70m x 30m", 12, false, false, false, "bom", false, "09:00-19:00", 80.0),
  CampoFutebol(8, "Gramado Dourado", R.drawable.campo_img2, "Alameda das Flores, 56 - Florianópolis", "gramado natural", "120m x 80m", 22, true, true, true, "excelente", false, "06:00-21:00", 180.0),
  CampoFutebol(9, "Estrela do Sul", R.drawable.campo_img, "Rua Marechal, 123 - Brasília", "gramado sintético", "85m x 40m", 14, true, false, false, "bom", false, "10:00-20:00", 130.0),
  CampoFutebol(10, "Arena Fut", R.drawable.campo_img2, "Av. Paulista, 1000 - São Paulo", "gramado sintético", "75m x 35m", 14, true, true, true, "bom", true, "08:00-23:00", 200.0),
  CampoFutebol(11, "Campo Estrela", R.drawable.campo_img, "Estrada Nova, 222 - Manaus", "gramado natural", "110m x 65m", 22, false, true, false, "regular", false, "09:00-18:00", 95.0),
  CampoFutebol(12, "Super Gramado", R.drawable.campo_img2, "Rua Rio Verde, 18 - Goiânia", "gramado sintético", "95m x 50m", 18, true, true, true, "ótimo", false, "08:00-21:00", 125.0),
  CampoFutebol(13, "Pelada da Semana", R.drawable.campo_img, "Rua das Palmeiras, 320 - Campinas", "gramado sintético", "80m x 40m", 16, false, false, true, "bom", false, "09:00-22:00", 100.0),
  CampoFutebol(14, "FutGramado", R.drawable.campo_img2, "Av. dos Atletas, 444 - Natal", "areia", "60m x 30m", 10, false, false, false, "bom", false, "07:00-19:00", 70.0),
  CampoFutebol(15, "Arena do Povo", R.drawable.campo_img, "Praça Central, 1 - Fortaleza", "gramado sintético", "90m x 50m", 20, true, true, false, "bom", false, "08:00-22:00", 150.0),
  CampoFutebol(16, "Gramado Perfeito", R.drawable.campo_img2, "Estrada Velha, 90 - Vitória", "gramado natural", "110m x 70m", 22, true, true, true, "excelente", false, "06:00-22:00", 200.0),
  CampoFutebol(17, "Society Esportes", R.drawable.campo_img, "Rua Esportiva, 210 - Curitiba", "gramado sintético", "80m x 40m", 16, true, true, false, "ótimo", false, "08:00-23:00", 120.0),
  CampoFutebol(18, "Campo Azul", R.drawable.campo_img2, "Av. Marítima, 999 - Rio de Janeiro", "gramado natural", "100m x 60m", 22, false, false, false, "regular", false, "10:00-20:00", 95.0),
  CampoFutebol(19, "FutShow", R.drawable.campo_img, "Rua das Flores, 77 - Belo Horizonte", "gramado sintético", "85m x 45m", 16, true, true, true, "ótimo", true, "08:00-22:00", 160.0),
  CampoFutebol(20, "Esporte Total", R.drawable.campo_img2, "Av. das Palmeiras, 55 - Porto Alegre", "gramado natural", "120m x 80m", 22, true, true, false, "excelente", false, "07:00-21:00", 180.0)
)

