package org.example.phase2.controller;

import jakarta.validation.Valid;
import org.example.phase2.dto.TaskRequestDto;
import org.example.phase2.dto.TaskResponseDto;
import org.example.phase2.service.TaskService;
import org.example.phase2.service.TaskServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public TaskResponseDto create(@Valid @RequestBody TaskRequestDto dto){
        return taskService.createTask(dto);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks(){
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public TaskResponseDto getById(@PathVariable Long id){
        return  taskService.getById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id,@Valid @RequestBody TaskRequestDto dto){
        return taskService.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        taskService.delete(id);
        return "Deleted successfully";
    }

    @GetMapping("/test")
    public String test() {
        return "WORKING";
    }
}
