package com.taskmanager.controllers
import com.taskmanager.services.TaskService


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(TaskController::class)
class TaskControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var taskService: TaskService 

    @Test
    fun `should get tasks`() {
        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk)
        // Dòng này gọi một phương thức không tồn tại, bạn cần xác nhận nó có trong TaskService
        // verify(taskService).getTasks() 
    }

    // Thêm các bài kiểm tra khác cho TaskController ở đây
}