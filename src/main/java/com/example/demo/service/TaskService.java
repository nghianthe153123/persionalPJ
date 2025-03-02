package com.example.demo.service;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Task> findAll() {
        if (taskRepository.findAll().isEmpty()) {
            throw new CustomException("There are no tasks in the database");
        }else{
            try{
                return taskRepository.findAll();
            }catch(Exception e){
                throw new CustomException("Error in findAll");
            }
        }
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }

    public Task save(Task task) {
        Long userId = task.getUserId();
        try {
            userRepository.existsById(userId);
        } catch (Exception e) {
            throw new CustomException("User id not found");
        }
        return taskRepository.save(task);
    }


}
