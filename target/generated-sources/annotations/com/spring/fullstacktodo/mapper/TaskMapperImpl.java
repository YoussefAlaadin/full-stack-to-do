package com.spring.fullstacktodo.mapper;

import com.spring.fullstacktodo.dto.TaskRequestDTO;
import com.spring.fullstacktodo.dto.TaskResponseDTO;
import com.spring.fullstacktodo.model.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-22T17:45:52+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( dto.getTitle() );
        task.setDescription( dto.getDescription() );
        task.setStatus( dto.getStatus() );
        task.setPriority( dto.getPriority() );

        return task;
    }

    @Override
    public TaskResponseDTO toResponseDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId( entity.getId() );
        taskResponseDTO.setTitle( entity.getTitle() );
        taskResponseDTO.setDescription( entity.getDescription() );
        taskResponseDTO.setStatus( entity.getStatus() );
        taskResponseDTO.setPriority( entity.getPriority() );
        taskResponseDTO.setCreatedAt( entity.getCreatedAt() );
        taskResponseDTO.setUpdatedAt( entity.getUpdatedAt() );

        return taskResponseDTO;
    }

    @Override
    public List<TaskResponseDTO> toResponseDtoList(List<Task> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TaskResponseDTO> list = new ArrayList<TaskResponseDTO>( entities.size() );
        for ( Task task : entities ) {
            list.add( toResponseDto( task ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(TaskRequestDTO dto, Task entity) {
        if ( dto == null ) {
            return;
        }

        entity.setTitle( dto.getTitle() );
        entity.setDescription( dto.getDescription() );
        entity.setStatus( dto.getStatus() );
        entity.setPriority( dto.getPriority() );
    }
}
