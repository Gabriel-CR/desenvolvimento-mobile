package com.example.iplay.ui.theme

import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


// Tema escuro
private val DarkColorScheme = darkColorScheme(
  primary = Blue80,
  onPrimary = White80,
  secondary = Green80,
  onSecondary = White80,
  surface = Blue40,
  onBackground = White40,
  onSurface = White40
)

// Tema claro
private val LightColorScheme = lightColorScheme(
  primary = Blue40,
  onPrimary = Black,
  secondary = Green40,
  onSecondary = White40,
  background = White80,
  surface = White80,
  onBackground = Blue40,
  onSurface = Blue40
)

@Composable
fun IPlayTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

// Card transparente com borda
val CardTransparentColors: Color
  @Composable get() = Color.Transparent

val CardTransparentBorder: BorderStroke
  @Composable get() = BorderStroke(
    width = 1.dp,
    color = MaterialTheme.colorScheme.primary
  )

// Botão primário
val ButtonPrimaryColors: ButtonColors
  @Composable get() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary
  )

// Botão secundário
val ButtonSecondaryColors: ButtonColors
  @Composable get() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = MaterialTheme.colorScheme.onSecondary
  )

// Botão transparente com borda e texto colorido
val ButtonTransparentColors: ButtonColors
  @Composable get() = ButtonDefaults.outlinedButtonColors(
    containerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.primary
  )

val ButtonTransparentBorder: BorderStroke
  @Composable get() = BorderStroke(
    width = 1.dp,
    color = MaterialTheme.colorScheme.primary
  )

// Textos primários
val PrimaryTextColor: Color
  @Composable get() = MaterialTheme.colorScheme.onBackground

// Textos secundários
val SecondaryTextColor: Color
  @Composable get() = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

// Ícones
val IconColor: Color
  @Composable get() = MaterialTheme.colorScheme.onBackground

// NavigationBar Colors
val NavigationBarColors: NavigationBarItemColors
  @Composable get() = NavigationBarItemDefaults.colors(
    selectedIconColor = IconColor,
    unselectedIconColor = SecondaryTextColor,
    selectedTextColor = IconColor,
    unselectedTextColor = SecondaryTextColor
  )
