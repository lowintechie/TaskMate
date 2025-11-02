package com.siriha.taskmatesmart.data.model

data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val isDone: Boolean = false,
    val userId: String = ""
)