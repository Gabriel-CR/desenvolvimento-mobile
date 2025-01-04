package com.example.tasksmanagerapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tasksmanagerapp.models.Task
import com.example.tasksmanagerapp.ui.components.AddTaskSection
import com.example.tasksmanagerapp.ui.components.TaskItem
import com.example.tasksmanagerapp.ui.components.TopBar
import com.example.tasksmanagerapp.ui.views.TasksViewModel
import kotlinx.coroutines.launch

@Composable
fun TasksScreen(viewModel: TasksViewModel = TasksViewModel(LocalContext.current)) {
  val progress by viewModel.progress.collectAsState()
  val isDarkTheme by viewModel.isDarkTheme.collectAsState()
  val snackbarHostState = remember { SnackbarHostState() }
  val context = LocalContext.current
  val tasks by viewModel.tasks.collectAsState()
  val scope = rememberCoroutineScope()

  MaterialTheme(
    colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
  ) {
    Scaffold(
      snackbarHost = { SnackbarHost(snackbarHostState) },
      topBar = { TopBar(context, viewModel) }
    ) { paddingValues ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues)
          .padding(16.dp)
      ) {
        Text("Progresso das Tarefas")

        LinearProgressIndicator(
          progress = { progress },
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .padding(bottom = 8.dp),
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
          items(tasks) { task ->
            TaskItem(
              task = task,
              onToggleCompletion = { viewModel.toggleTaskCompletion(task) },
              onDelete = {
                viewModel.removeTask(task)
                scope.launch {
                  val result = snackbarHostState.showSnackbar(
                    message = "Tarefa removida",
                    actionLabel = "Desfazer",
                    duration = SnackbarDuration.Short
                  )
                  if (result == SnackbarResult.ActionPerformed) { viewModel.undoDelete() }
                }
              }
            )
          }
        }

        AddTaskSection { name, category, priority, dueDate ->
          viewModel.addTask(Task(name, false, category, priority, dueDate))
        }
      }
    }
  }
}
