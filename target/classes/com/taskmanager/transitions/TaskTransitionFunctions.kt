package com.taskmanager.transitions

import com.taskmanager.commands.CompleteTask
import com.taskmanager.commands.CreateTask
import com.taskmanager.commands.UpdateTask
import com.taskmanager.events.TaskCompleted
import com.taskmanager.events.TaskCreated
import com.taskmanager.events.TaskUpdated
import com.taskmanager.aggregates.Task
import com.taskmanager.aggregates.TaskState

fun handleCommand(command: Any): Any { // Sử dụng Any thay cho TaskCommand và TaskEvent
    return when (command) {
        is CreateTask -> TaskCreated("generatedID", command.title, command.description)
        is UpdateTask -> TaskUpdated(command.id, command.title, command.description)
        is CompleteTask -> TaskCompleted(command.id)
        else -> throw IllegalArgumentException("Unknown command: $command")
    }
}

fun applyEvent(event: Any, task: Task): Task { // Sử dụng Any thay cho TaskEvent
    return when (event) {
        is TaskCreated -> Task(event.id, event.title, event.description, TaskState.PENDING)
        is TaskUpdated -> task.copy(title = event.title, description = event.description)
        is TaskCompleted -> task.copy(status = TaskState.COMPLETED)
        else -> throw IllegalArgumentException("Unknown event: $event")
    }
}