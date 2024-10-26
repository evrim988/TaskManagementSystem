package org.example.taskmanagementsystemproject.mapper;

import org.example.taskmanagementsystemproject.dto.request.task.TaskRequestDto;
import org.example.taskmanagementsystemproject.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMappper {

    TaskMappper INSTANCE = Mappers.getMapper(TaskMappper.class);

    Task fromTaskRequestDto(TaskRequestDto taskRequestDto);
}
