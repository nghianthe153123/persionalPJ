package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(TaskController.class)
@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    private static final Logger log = LoggerFactory.getLogger(TaskControllerTest.class);
    @Mock
    private TaskService taskService;
    private Task taskRequest;
    private Task taskResponse;

    @BeforeEach
    void initData(){
//        taskRequest = new Task(1L, "Test Task", "test description", 1L);

        taskResponse = Task.builder()
                .id(1L)
                .name("Test Task")
                .description("test description")
                .userId(1L)
                .build();
    }

    @Test
    void createTask(){
        log.info("Test create task");
    }

}
