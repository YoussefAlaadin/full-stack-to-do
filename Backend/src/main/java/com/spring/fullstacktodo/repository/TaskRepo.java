package com.spring.fullstacktodo.repository;

import com.spring.fullstacktodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {



    List<Task> findAllByUserIdOrderByPriorityDesc(Long userId);

    List<Task> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    List<Task> findByUserIdAndPriority(Long userId, Task.TaskPriority priority);

    List<Task> findByUserIdAndStatus(Long userId, Task.TaskStatus status);

    List<Task> findByUserIdAndTitleContainingIgnoreCase(Long userId, String title);

    java.util.Optional<Task> findByIdAndUserId(Long id, Long userId);

    void deleteByIdAndUserId(Long id, Long userId);

    void deleteAllByUserId(Long userId);

}
