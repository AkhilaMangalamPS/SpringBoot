package org.example.phase2.service;

import org.example.phase2.dto.TaskRequestDto;
import org.example.phase2.dto.TaskResponseDto;
import org.example.phase2.exception.ResourceNotFoundException;
import org.example.phase2.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService{

    private List<Task> tasks = new ArrayList<>();
    private Long idCounter = 1L;
    @Override
    public TaskResponseDto createTask(TaskRequestDto dto) {
        Task task = new Task(idCounter++, dto.title(), dto.status());
        tasks.add(task);
        return new TaskResponseDto(task.getId(),task.getTitle(),task.getStatus());
    }

    @Override
    public List<TaskResponseDto> getAllTask() {
        return tasks.stream()
                .map(task -> new TaskResponseDto(
                        task.getId(),
                        task.getTitle(),
                        task.getStatus()
                        ))
                .toList();
    }

    @Override
    public TaskResponseDto getById(Long id){
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("task not found"));
        return new TaskResponseDto(task.getId(),task.getTitle(),task.getStatus());
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto dto) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("task not found"));
        task.setTitle(dto.title());
        task.setStatus(dto.status());

        return new TaskResponseDto(task.getId(),task.getTitle(),task.getStatus());
    }

    @Override
    public void delete(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));

    }
}
