package com.spring.fullstacktodo.controller;

import com.spring.fullstacktodo.dto.TaskRequestDTO;
import com.spring.fullstacktodo.dto.TaskResponseDTO;
import com.spring.fullstacktodo.model.Task;
import com.spring.fullstacktodo.service.TaskService;
import com.spring.fullstacktodo.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow requests from  frontend
public class TaskController {

    private final TaskService taskService;

    // Create a new task
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO, userId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get all tasks
    @GetMapping()
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<TaskResponseDTO> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return taskService.getTaskById(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskRequestDTO) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.updateTask(id, taskRequestDTO, userId));
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        taskService.deleteTask(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Mark task as completed
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponseDTO> markAsCompleted(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsCompleted(id, userId));
    }

    // Mark task as in progress
    @PatchMapping("/{id}/in-progress")
    public ResponseEntity<TaskResponseDTO> markAsInProgress(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsInProgress(id, userId));
    }

    // Mark task as incompleted
    @PatchMapping("/{id}/todo")
    public ResponseEntity<TaskResponseDTO> markTaskAsTodo(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsToDo(id, userId));
    }

    // Mark task as urgent
    @PatchMapping("/{id}/urgent")
    public ResponseEntity<TaskResponseDTO> markAsUrgent(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsUrgent(id, userId));
    }

    // Mark task as high
    @PatchMapping("/{id}/high")
    public ResponseEntity<TaskResponseDTO> markAsHigh(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsHigh(id, userId));
    }

    // Mark task as medium
    @PatchMapping("/{id}/medium")
    public ResponseEntity<TaskResponseDTO> markAsMedium(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsMedium(id, userId));
    }

    // Mark task as low
    @PatchMapping("/{id}/low")
    public ResponseEntity<TaskResponseDTO> markAsLow(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(taskService.markTaskAsLow(id, userId));
    }

    // Delete all tasks
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTasks() {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        taskService.deleteAllTasks(userId);
        return ResponseEntity.noContent().build();
    }

    // Get tasks by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByStatus(@PathVariable Task.TaskStatus status) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<TaskResponseDTO> tasks = taskService.getTasksByStatus(status, userId);
        return ResponseEntity.ok(tasks);
    }

    // Get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByPriority(@PathVariable Task.TaskPriority priority) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<TaskResponseDTO> tasks = taskService.getTasksByPriority(priority, userId);
        return ResponseEntity.ok(tasks);
    }

//    // Get tasks by status and priority
//    @GetMapping("/status-priority/{status}/{priority}")
//    public ResponseEntity<List<TaskResponseDTO>> getTasksByStatusAndPriority(@PathVariable Task.TaskStatus status, @PathVariable Task.TaskPriority priority) {
//        List<TaskResponseDTO> tasks = taskService.getTasksByStatusAndPriority(status, priority);
//        return ResponseEntity.ok(tasks);
//    }

    // Search tasks by title
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponseDTO>> searchTasks(@RequestParam String title) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<TaskResponseDTO> tasks = taskService.searchTasksByTitle(title, userId);
        return ResponseEntity.ok(tasks);
    }

    // Get all tasks ordered by priority
    @GetMapping("/order/priority")
    public ResponseEntity<List<TaskResponseDTO>> getTasksOrderedByPriority() {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<TaskResponseDTO> tasks = taskService.getAllTasksOrderedByPriority(userId);
        return ResponseEntity.ok(tasks);
    }

    // Get all tasks ordered by date
    @GetMapping("/order/date")
    public ResponseEntity<List<TaskResponseDTO>> getTasksOrderedByDate() {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        List<TaskResponseDTO> tasks = taskService.getAllTasksOrderedByDate(userId);
        return ResponseEntity.ok(tasks);
    }
}
