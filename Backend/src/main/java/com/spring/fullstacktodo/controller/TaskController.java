package com.spring.fullstacktodo.controller;

import com.spring.fullstacktodo.model.Task;
import com.spring.fullstacktodo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow requests from React frontend
public class TaskController {

    private final TaskService taskService;

    // Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get all tasks
    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        try {
            Task updatedTask = taskService.updateTask(id, taskDetails);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as completed
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> markAsCompleted(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsCompleted(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as in progress
    @PatchMapping("/{id}/in-progress")
    public ResponseEntity<Task> markAsInProgress(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsInProgress(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as incompleted
    @PatchMapping("/{id}/todo")
    public ResponseEntity<Task> markTaskAsTodo(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsToDo(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as urgent
    @PatchMapping("/{id}/urgent")
    public ResponseEntity<Task> markAsUrgent(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsUrgent(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as high
    @PatchMapping("/{id}/high")
    public ResponseEntity<Task> markAsHigh(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsHigh(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as medium
    @PatchMapping("/{id}/medium")
    public ResponseEntity<Task> markAsMedium(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsMedium(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark task as low
    @PatchMapping("/{id}/low")
    public ResponseEntity<Task> markAsLow(@PathVariable Long id) {
        try {
            Task task = taskService.markTaskAsLow(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete all tasks
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTasks() {
        taskService.deleteAllTasks();
        return ResponseEntity.noContent().build();
    }

    // Get tasks by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Task.TaskStatus status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    // Get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Task.TaskPriority priority) {
        List<Task> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    // Get tasks by status and priority
    @GetMapping("/status-priority/{status}/{priority}")
    public ResponseEntity<List<Task>> getTasksByStatusAndPriority(@PathVariable Task.TaskStatus status, @PathVariable Task.TaskPriority priority) {
        List<Task> tasks = taskService.getTasksByStatusAndPriority(status, priority);
        return ResponseEntity.ok(tasks);
    }

    // Search tasks by title
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String title) {
        List<Task> tasks = taskService.searchTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    // Get all tasks ordered by priority
    @GetMapping("/order/priority")
    public ResponseEntity<List<Task>> getTasksOrderedByPriority() {
        List<Task> tasks = taskService.getAllTasksOrderedByPriority();
        return ResponseEntity.ok(tasks);
    }

    // Get all tasks ordered by date
    @GetMapping("/order/date")
    public ResponseEntity<List<Task>> getTasksOrderedByDate() {
        List<Task> tasks = taskService.getAllTasksOrderedByDate();
        return ResponseEntity.ok(tasks);

    }
}
