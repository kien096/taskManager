package com.taskmanager.services

import com.taskmanager.commands.CompleteTask
import com.taskmanager.commands.CreateTask
import com.taskmanager.commands.UpdateTask
import com.taskmanager.aggregates.Task
import com.taskmanager.aggregates.TaskState
import com.taskmanager.events.TaskCreated
import com.taskmanager.events.TaskCompleted
import com.taskmanager.events.TaskUpdated
import com.taskmanager.repositories.TaskRepository
import java.util.UUID

class TaskService(private val repository: TaskRepository) {
    fun createTask(command: CreateTask): Task {
        val id = UUID.randomUUID().toString()
        val task = Task(id, command.title, command.description, TaskState.PENDING)
        
        repository.save(task)
        
        return task
    }

    fun updateTask(command: UpdateTask): Task {
        val existingTask = repository.findById(command.id) ?: throw IllegalArgumentException("No task found with id: ${command.id}")

        val updatedTask = existingTask.copy(title = command.title, description = command.description)
        repository.save(updatedTask)
        
        return updatedTask
    }

    fun completeTask(command: CompleteTask): Task {
        val existingTask = repository.findById(command.id) ?: throw IllegalArgumentException("No task found with id: ${command.id}")

        if (existingTask.status == TaskState.COMPLETED) {
            throw IllegalStateException("Task is already completed")
        }

        val completedTask = existingTask.copy(status = TaskState.COMPLETED)
        repository.save(completedTask)
        
        return completedTask
    }
}
