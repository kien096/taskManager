package com.taskmanager.services

import com.taskmanager.commands.CreateTask
import com.taskmanager.repositories.TaskRepository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private lateinit var taskRepository: TaskRepository

    @InjectMocks
    private lateinit var taskService: TaskService

    @Test
    fun `should create a task`() {
        val command = CreateTask("Test Title", "Test Description") 
        val task = taskService.createTask(command) 
        verify(taskRepository).save(task)
    }

    // Thêm các bài kiểm tra khác cho TaskService ở đây
}
