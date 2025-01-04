package com.example.tasksmanagerapp.ui.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tasksmanagerapp.models.TaskCategory
import com.example.tasksmanagerapp.models.TaskPriority
import java.util.Calendar

@Composable
fun AddTaskSection(onAddTask: (String, TaskCategory, TaskPriority, String?) -> Unit) {
  var taskName by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf(TaskCategory.CASA) }
  var selectedPriority by remember { mutableStateOf(TaskPriority.MEDIA) }
  var selectedDate by remember { mutableStateOf<String?>(null) }
  val context = LocalContext.current

  Column {
    OutlinedTextField(
      value = taskName,
      onValueChange = { taskName = it },
      label = { Text("Nova tarefa") },
      modifier = Modifier.fillMaxWidth()
    )

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      DropdownMenuBox("Categoria", TaskCategory.values().map { it.name }) {
        selectedCategory = TaskCategory.valueOf(it)
      }
      DropdownMenuBox("Prioridade", TaskPriority.values().map { it.name }) {
        selectedPriority = TaskPriority.valueOf(it)
      }
    }

    // Seletor de data com DatePicker
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
      Text(
        text = "Data de vencimento: ${selectedDate ?: "Não definida"}",
        modifier = Modifier.weight(1f)
      )
      Button(onClick = {
        showDatePicker(context) { date ->
          selectedDate = date
        }
      }) {
        Text("Selecionar Data")
      }
    }

    Button(
      onClick = {
        if (taskName.isNotBlank()) {
          onAddTask(taskName, selectedCategory, selectedPriority, selectedDate)
          taskName = ""
          selectedDate = null
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)
    ) {
      Text("Adicionar Tarefa")
    }
  }
}

@SuppressLint("DefaultLocale")
fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
  val calendar = Calendar.getInstance()
  val year = calendar.get(Calendar.YEAR)
  val month = calendar.get(Calendar.MONTH)
  val day = calendar.get(Calendar.DAY_OF_MONTH)

  val datePickerDialog = DatePickerDialog(
    context,
    { _, selectedYear, selectedMonth, selectedDay ->
      val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
      onDateSelected(formattedDate)
    },
    year, month, day
  )
  datePickerDialog.show()
}
