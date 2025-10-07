package com.spring.fullstacktodo.service;

import com.spring.fullstacktodo.model.Task;
import com.spring.fullstacktodo.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
    }

    // Update task
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        return taskRepo.save(task);
    }

    // Delete task
    public void deleteTask(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        taskRepo.delete(task);
    }

    // Delete all tasks
    public void deleteAllTasks() {
        taskRepo.deleteAll();
    }

    // Mark task as completed
    public Task markTaskAsCompleted(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setStatus(Task.TaskStatus.DONE);
        return taskRepo.save(task);
    }

    // Mark task as in progress
    public Task markTaskAsInProgress(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setStatus(Task.TaskStatus.IN_PROGRESS);
        return taskRepo.save(task);
    }

    // Mark task as to do
    public Task markTaskAsToDo(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setStatus(Task.TaskStatus.TODO);
        return taskRepo.save(task);
    }

    // Mark task as urgent
    public Task markTaskAsUrgent(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setPriority(Task.TaskPriority.URGENT);
        return taskRepo.save(task);
    }

    // Mark task as high
    public Task markTaskAsHigh(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setPriority(Task.TaskPriority.HIGH);
        return taskRepo.save(task);
    }

    // Mark task as medium
    public Task markTaskAsMedium(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setPriority(Task.TaskPriority.MEDIUM);
        return taskRepo.save(task);
    }

    // Mark task as low
    public Task markTaskAsLow(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setPriority(Task.TaskPriority.LOW);
        return taskRepo.save(task);
    }


    // Get task by status
    public List<Task> getTasksByStatus(Task.TaskStatus status) {
        return taskRepo.findByStatus(status);
    }

    // Get task by status and priority
    public List<Task> getTasksByStatusAndPriority(Task.TaskStatus status, Task.TaskPriority priority) {
        return taskRepo.findByStatusAndPriority(status, priority);
    }

    // Get tasks by priority
    public List<Task> getTasksByPriority(Task.TaskPriority priority) {
        return taskRepo.findByPriority(priority);
    }

    // Search tasks by title
    public List<Task> searchTasksByTitle(String title) {
        return taskRepo.findByTitleContainingIgnoreCase(title);
    }

    // Get all tasks ordered by priority
    public List<Task> getAllTasksOrderedByPriority() {
        return taskRepo.findAllByOrderByPriorityDesc();
    }

    // Get all tasks ordered by created date
    public List<Task> getAllTasksOrderedByDate() {
        return taskRepo.findAllByOrderByCreatedAtDesc();
    }

}

