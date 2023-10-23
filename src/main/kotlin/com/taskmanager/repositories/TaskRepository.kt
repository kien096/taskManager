package com.taskmanager.repositories

import com.taskmanager.aggregates.Task

interface TaskRepository {
    fun save(task: Task): Task
    fun findById(id: String): Task?
}
