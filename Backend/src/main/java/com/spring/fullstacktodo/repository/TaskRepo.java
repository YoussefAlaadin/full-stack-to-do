package com.spring.fullstacktodo.repository;

import com.spring.fullstacktodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {


    List<Task> findByPriority(Task.TaskPriority priority);

    List<Task> findByStatus(Task.TaskStatus status);

    List<Task> findByStatusAndPriority(Task.TaskStatus status, Task.TaskPriority priority);

    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findAllByOrderByPriorityDesc();

    List<Task> findAllByOrderByCreatedAtDesc();


}
