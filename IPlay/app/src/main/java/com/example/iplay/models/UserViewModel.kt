package com.example.iplay.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

  private val _user = mutableStateOf(User(name = "Usuário", password = "senha123"))
  val userView: State<User?> get() = _user

  init {
    _user.value = User(name = "Gabriel Castro", password = "123456")
  }

  fun updateUser(user: User) {
    _user.value = user
  }

  fun getName(): String {
    return _user.value.name
  }
}