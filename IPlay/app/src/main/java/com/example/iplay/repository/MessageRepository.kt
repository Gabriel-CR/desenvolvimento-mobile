package com.example.iplay.repository

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MessageRepository {
  private val db = Firebase.database
  private val myRef = db.reference.child("messages")

  fun getMessages(onDataChanged: (List<String>) -> Unit) {
    val messageListener = object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        val newMessages = snapshot.children.map { it.getValue(String::class.java) ?: "" }
        onDataChanged(newMessages)
      }

      override fun onCancelled(error: DatabaseError) {
        // Handle the error if needed
      }
    }
    myRef.addValueEventListener(messageListener)
  }

  fun sendMessage(message: String) {
    if (message.isNotBlank()) {
      myRef.push().setValue(message)
    }
  }
}
