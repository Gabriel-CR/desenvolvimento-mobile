package com.example.tasksmanagerapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tasksmanagerapp.models.Task
import com.example.tasksmanagerapp.models.TaskPriority

@Composable
fun TaskItem(task: Task, onToggleCompletion: () -> Unit, onDelete: () -> Unit) {
  val backgroundColor = when (task.priority) {
    TaskPriority.BAIXA -> Color(0xFFC8E6C9)
    TaskPriority.MEDIA -> Color(0xFFFFF59D)
    TaskPriority.ALTA -> Color(0xFFFFCDD2)
  }

  AnimatedVisibility(visible = true) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .background(backgroundColor, RoundedCornerShape(8.dp))
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Checkbox(checked = task.isCompleted, onCheckedChange = { onToggleCompletion() })

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = task.name,
          color = if (task.isCompleted) Color.Gray else Color.Black
        )

        if (!task.dueDate.isNullOrBlank()) {
          Text(
            text = "Vence em: ${task.dueDate}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
          )
        }
      }
    }
  }
}
