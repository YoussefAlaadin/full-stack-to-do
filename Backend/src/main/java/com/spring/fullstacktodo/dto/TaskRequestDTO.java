package com.spring.fullstacktodo.dto;

import com.spring.fullstacktodo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private String title;
    private String description;
    private Task.TaskStatus status;
    private Task.TaskPriority priority;
}
