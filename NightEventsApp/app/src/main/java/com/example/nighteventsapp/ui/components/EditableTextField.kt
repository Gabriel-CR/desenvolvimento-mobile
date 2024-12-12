package com.example.nighteventsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun EditableTextField(value: String, onValueChange: (String) -> Unit) {
  var textFieldValue by remember { mutableStateOf(TextFieldValue(value)) }
  BasicTextField(
    value = textFieldValue,
    onValueChange = {
      textFieldValue = it
      onValueChange(it.text)
    },
    modifier = Modifier
      .background(MaterialTheme.colorScheme.onBackground)
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .fillMaxWidth(),
    textStyle = MaterialTheme.typography.bodyLarge.copy(
      color = MaterialTheme.colorScheme.surface
    )
  )
}