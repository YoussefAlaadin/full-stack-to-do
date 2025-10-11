package com.spring.fullstacktodo.controller;

import com.spring.fullstacktodo.dto.TaskRequestDTO;
import com.spring.fullstacktodo.dto.TaskResponseDTO;
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
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get all tasks
    @GetMapping()
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskRequestDTO));
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Mark task as completed
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponseDTO> markAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsCompleted(id));
    }

    // Mark task as in progress
    @PatchMapping("/{id}/in-progress")
    public ResponseEntity<TaskResponseDTO> markAsInProgress(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsInProgress(id));
    }

    // Mark task as incompleted
    @PatchMapping("/{id}/todo")
    public ResponseEntity<TaskResponseDTO> markTaskAsTodo(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsToDo(id));
    }

    // Mark task as urgent
    @PatchMapping("/{id}/urgent")
    public ResponseEntity<TaskResponseDTO> markAsUrgent(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsUrgent(id));
    }

    // Mark task as high
    @PatchMapping("/{id}/high")
    public ResponseEntity<TaskResponseDTO> markAsHigh(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsHigh(id));
    }

    // Mark task as medium
    @PatchMapping("/{id}/medium")
    public ResponseEntity<TaskResponseDTO> markAsMedium(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsMedium(id));
    }

    // Mark task as low
    @PatchMapping("/{id}/low")
    public ResponseEntity<TaskResponseDTO> markAsLow(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsLow(id));
    }

    // Delete all tasks
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTasks() {
        taskService.deleteAllTasks();
        return ResponseEntity.noContent().build();
    }

    // Get tasks by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByStatus(@PathVariable Task.TaskStatus status) {
        List<TaskResponseDTO> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    // Get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByPriority(@PathVariable Task.TaskPriority priority) {
        List<TaskResponseDTO> tasks = taskService.getTasksByPriority(priority);
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
        List<TaskResponseDTO> tasks = taskService.searchTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    // Get all tasks ordered by priority
    @GetMapping("/order/priority")
    public ResponseEntity<List<TaskResponseDTO>> getTasksOrderedByPriority() {
        List<TaskResponseDTO> tasks = taskService.getAllTasksOrderedByPriority();
        return ResponseEntity.ok(tasks);
    }

    // Get all tasks ordered by date
    @GetMapping("/order/date")
    public ResponseEntity<List<TaskResponseDTO>> getTasksOrderedByDate() {
        List<TaskResponseDTO> tasks = taskService.getAllTasksOrderedByDate();
        return ResponseEntity.ok(tasks);
    }
}
