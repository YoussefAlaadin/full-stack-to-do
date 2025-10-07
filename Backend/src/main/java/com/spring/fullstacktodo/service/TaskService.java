/*
package com.spring.fullstacktodo.service;

import com.spring.fullstacktodo.model.Task;
import com.spring.fullstacktodo.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;

    // Create a new task
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    // Get task by id
    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    // Update task
    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }
}
*/
