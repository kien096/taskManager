package com.taskmanager.aggregates

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val status: TaskState
)
