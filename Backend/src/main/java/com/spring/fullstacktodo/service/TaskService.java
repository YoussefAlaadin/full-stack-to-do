package com.spring.fullstacktodo.service;

import com.spring.fullstacktodo.dto.TaskRequestDTO;
import com.spring.fullstacktodo.dto.TaskResponseDTO;
import com.spring.fullstacktodo.exception.TaskNotFoundException;
import com.spring.fullstacktodo.mapper.TaskMapper;
import com.spring.fullstacktodo.model.Task;
import com.spring.fullstacktodo.model.User;
import com.spring.fullstacktodo.repository.TaskRepo;
import com.spring.fullstacktodo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final UserRepo userRepo;

    // Create a new task (scoped to user)
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO, Long userId) {
        Task task = taskMapper.toEntity(taskRequestDTO);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        task.setUser(user);
        Task savedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(savedTask);
    }

    // Get all tasks for a user
    public List<TaskResponseDTO> getAllTasks(Long userId) {
        List<Task> tasks = taskRepo.findAllByUserIdOrderByCreatedAtDesc(userId);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get task by id (scoped to user)
    public Optional<TaskResponseDTO> getTaskById(Long id, Long userId) {
        return taskRepo.findByIdAndUserId(id, userId)
                .map(taskMapper::toResponseDto);
    }

    // Update task
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskMapper.updateEntityFromDto(taskRequestDTO, task);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Delete task
    public void deleteTask(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepo.delete(task);
    }

    // Delete all tasks for a user
    public void deleteAllTasks(Long userId) {
        taskRepo.deleteAllByUserId(userId);
    }

    // Mark task as completed
    public TaskResponseDTO markTaskAsCompleted(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.TaskStatus.DONE);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as in progress
    public TaskResponseDTO markTaskAsInProgress(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.TaskStatus.IN_PROGRESS);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as to do
    public TaskResponseDTO markTaskAsToDo(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.TaskStatus.TODO);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as urgent
    public TaskResponseDTO markTaskAsUrgent(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.URGENT);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as high
    public TaskResponseDTO markTaskAsHigh(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.HIGH);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as medium
    public TaskResponseDTO markTaskAsMedium(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.MEDIUM);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as low
    public TaskResponseDTO markTaskAsLow(Long id, Long userId) {
        Task task = taskRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.LOW);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Get task by status
    public List<TaskResponseDTO> getTasksByStatus(Task.TaskStatus status, Long userId) {
        List<Task> tasks = taskRepo.findByUserIdAndStatus(userId, status);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get tasks by priority
    public List<TaskResponseDTO> getTasksByPriority(Task.TaskPriority priority, Long userId) {
        List<Task> tasks = taskRepo.findByUserIdAndPriority(userId, priority);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Search tasks by title
    public List<TaskResponseDTO> searchTasksByTitle(String title, Long userId) {
        List<Task> tasks = taskRepo.findByUserIdAndTitleContainingIgnoreCase(userId, title);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get all tasks ordered by priority
    public List<TaskResponseDTO> getAllTasksOrderedByPriority(Long userId) {
        List<Task> tasks = taskRepo.findAllByUserIdOrderByPriorityDesc(userId);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get all tasks ordered by created date
    public List<TaskResponseDTO> getAllTasksOrderedByDate(Long userId) {
        List<Task> tasks = taskRepo.findAllByUserIdOrderByCreatedAtDesc(userId);
        return taskMapper.toResponseDtoList(tasks);
    }
}
