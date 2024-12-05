package com.example.planetapp.models

import com.example.planetapp.R

data class Planet(
  val id: Int,
  val name: String,
  val type: String,
  val galaxy: String,
  val distanceFromSun: String,
  val diameter: String,
  val characteristics: String,
  val imageRes: Int,
  var isFavorite: Boolean = false
)

val planetList = listOf(
  Planet(
    id = 1,
    name = "Mercury",
    type = "Terrestrial",
    galaxy = "Milky Way",
    distanceFromSun = "57.9 million km",
    diameter = "4,880 km",
    characteristics = "Closest planet to the Sun, has no atmosphere.",
    imageRes = R.drawable.mercurio
  ),
  Planet(
    id = 2,
    name = "Venus",
    type = "Terrestrial",
    galaxy = "Milky Way",
    distanceFromSun = "108.2 million km",
    diameter = "12,104 km",
    characteristics = "Hottest planet, thick atmosphere of CO2.",
    imageRes = R.drawable.venus
  ),
  Planet(
    id = 3,
    name = "Earth",
    type = "Terrestrial",
    galaxy = "Milky Way",
    distanceFromSun = "149.6 million km",
    diameter = "12,742 km",
    characteristics = "Supports life, has water and atmosphere.",
    imageRes = R.drawable.terra
  ),
  Planet(
    id = 4,
    name = "Mars",
    type = "Terrestrial",
    galaxy = "Milky Way",
    distanceFromSun = "227.9 million km",
    diameter = "6,779 km",
    characteristics = "Known as the Red Planet, potential for colonization.",
    imageRes = R.drawable.marte
  ),
  Planet(
    id = 5,
    name = "Jupiter",
    type = "Gas Giant",
    galaxy = "Milky Way",
    distanceFromSun = "778.3 million km",
    diameter = "139,820 km",
    characteristics = "Largest planet, has a giant storm called the Great Red Spot.",
    imageRes = R.drawable.jupiter
  ),
  Planet(
    id = 6,
    name = "Saturn",
    type = "Gas Giant",
    galaxy = "Milky Way",
    distanceFromSun = "1.43 billion km",
    diameter = "116,460 km",
    characteristics = "Famous for its extensive ring system.",
    imageRes = R.drawable.saturno
  ),
  Planet(
    id = 7,
    name = "Uranus",
    type = "Ice Giant",
    galaxy = "Milky Way",
    distanceFromSun = "2.87 billion km",
    diameter = "50,724 km",
    characteristics = "Rotates on its side, has a blue-green hue due to methane.",
    imageRes = R.drawable.urano
  ),
  Planet(
    id = 8,
    name = "Neptune",
    type = "Ice Giant",
    galaxy = "Milky Way",
    distanceFromSun = "4.5 billion km",
    diameter = "49,244 km",
    characteristics = "Known for its deep blue color and strong winds.",
    imageRes = R.drawable.netuno
  ),
  Planet(
    id = 9,
    name = "Pluto",
    type = "Dwarf Planet",
    galaxy = "Milky Way",
    distanceFromSun = "5.9 billion km",
    diameter = "2,377 km",
    characteristics = "A dwarf planet in the Kuiper Belt, has an eccentric orbit.",
    imageRes = R.drawable.plutao
  )
)
