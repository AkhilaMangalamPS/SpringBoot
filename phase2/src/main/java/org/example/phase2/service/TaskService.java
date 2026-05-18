package org.example.phase2.service;

import org.example.phase2.dto.TaskRequestDto;
import org.example.phase2.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto dto);
    List<TaskResponseDto> getAllTask();
    TaskResponseDto update(Long id,TaskRequestDto dto);
    TaskResponseDto getById(Long id);
    void delete(Long id);
}
