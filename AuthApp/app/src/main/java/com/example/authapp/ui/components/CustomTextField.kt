package com.example.authapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  icon: ImageVector,
  isPassword: Boolean = false
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text(label) },
    leadingIcon = { Icon(icon, contentDescription = null) },
    visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(8.dp)
  )
}
