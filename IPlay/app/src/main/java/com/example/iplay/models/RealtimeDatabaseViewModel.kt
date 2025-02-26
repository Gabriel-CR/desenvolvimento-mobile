package com.example.iplay.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.iplay.repository.MessageRepository

class RealtimeDatabaseViewModel : ViewModel() {
  private val repository = MessageRepository()

  private val _messages = mutableStateOf<List<String>>(emptyList())
  val messages: State<List<String>> = _messages

  fun fetchMessages() {
    repository.getMessages { messages ->
      _messages.value = messages
    }
  }

  fun sendMessage(message: String) {
    repository.sendMessage(message)
  }
}
