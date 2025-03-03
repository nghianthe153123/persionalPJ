package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Task save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        return taskService.save(task);
    }
}
