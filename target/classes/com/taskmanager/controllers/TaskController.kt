package com.taskmanager.controllers

import com.taskmanager.commands.CompleteTask
import com.taskmanager.commands.UpdateTask
import com.taskmanager.commands.CreateTask
import com.taskmanager.aggregates.Task
import com.taskmanager.services.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping
    fun createTask(@RequestBody command: CreateTask): ResponseEntity<Task> {
        val task = taskService.createTask(command)
        return ResponseEntity.ok(task)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: String, @RequestBody command: UpdateTask): ResponseEntity<Task> {
        if (id != command.id) {
            return ResponseEntity.badRequest().build()
        }
        val task = taskService.updateTask(command)
        return ResponseEntity.ok(task)
    }

    @PostMapping("/{id}/complete")
    fun completeTask(@PathVariable id: String): ResponseEntity<Task> {
        val command = CompleteTask(id)
        val task = taskService.completeTask(command)
        return ResponseEntity.ok(task)
    }
}
