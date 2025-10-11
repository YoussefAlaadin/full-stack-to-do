package com.spring.fullstacktodo.mapper;

import com.spring.fullstacktodo.dto.TaskRequestDTO;
import com.spring.fullstacktodo.dto.TaskResponseDTO;
import com.spring.fullstacktodo.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskRequestDTO dto);

    TaskResponseDTO toResponseDto(Task entity);

    List<TaskResponseDTO> toResponseDtoList(List<Task> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(TaskRequestDTO dto, @MappingTarget Task entity);
}
