package com.spring.fullstacktodo.service;

import com.spring.fullstacktodo.dto.TaskRequestDTO;
import com.spring.fullstacktodo.dto.TaskResponseDTO;
import com.spring.fullstacktodo.exception.TaskNotFoundException;
import com.spring.fullstacktodo.mapper.TaskMapper;
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
    private final TaskMapper taskMapper;

    // Create a new task
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = taskMapper.toEntity(taskRequestDTO);
        Task savedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(savedTask);
    }

    // Get all tasks
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get task by id
    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepo.findById(id)
                .map(taskMapper::toResponseDto);
    }

    // Update task
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskMapper.updateEntityFromDto(taskRequestDTO, task);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Delete task
    public void deleteTask(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepo.delete(task);
    }

    // Delete all tasks
    public void deleteAllTasks() {
        taskRepo.deleteAll();
    }

    // Mark task as completed
    public TaskResponseDTO markTaskAsCompleted(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.TaskStatus.DONE);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as in progress
    public TaskResponseDTO markTaskAsInProgress(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.TaskStatus.IN_PROGRESS);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as to do
    public TaskResponseDTO markTaskAsToDo(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.TaskStatus.TODO);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as urgent
    public TaskResponseDTO markTaskAsUrgent(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.URGENT);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as high
    public TaskResponseDTO markTaskAsHigh(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.HIGH);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as medium
    public TaskResponseDTO markTaskAsMedium(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.MEDIUM);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Mark task as low
    public TaskResponseDTO markTaskAsLow(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setPriority(Task.TaskPriority.LOW);
        Task updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    // Get task by status
    public List<TaskResponseDTO> getTasksByStatus(Task.TaskStatus status) {
        List<Task> tasks = taskRepo.findByStatus(status);
        return taskMapper.toResponseDtoList(tasks);
    }

//    // Get task by status and priority
//    public List<TaskResponseDTO> getTasksByStatusAndPriority(Task.TaskStatus status, Task.TaskPriority priority) {
//        List<Task> tasks = taskRepo.findByStatusAndPriority(status, priority);
//        return taskMapper.toResponseDtoList(tasks);
//    }

    // Get tasks by priority
    public List<TaskResponseDTO> getTasksByPriority(Task.TaskPriority priority) {
        List<Task> tasks = taskRepo.findByPriority(priority);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Search tasks by title
    public List<TaskResponseDTO> searchTasksByTitle(String title) {
        List<Task> tasks = taskRepo.findByTitleContainingIgnoreCase(title);
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get all tasks ordered by priority
    public List<TaskResponseDTO> getAllTasksOrderedByPriority() {
        List<Task> tasks = taskRepo.findAllByOrderByPriorityDesc();
        return taskMapper.toResponseDtoList(tasks);
    }

    // Get all tasks ordered by created date
    public List<TaskResponseDTO> getAllTasksOrderedByDate() {
        List<Task> tasks = taskRepo.findAllByOrderByCreatedAtDesc();
        return taskMapper.toResponseDtoList(tasks);
    }
}

