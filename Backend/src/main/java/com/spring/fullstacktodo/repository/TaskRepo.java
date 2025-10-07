package com.spring.fullstacktodo.repository;

import com.spring.fullstacktodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
