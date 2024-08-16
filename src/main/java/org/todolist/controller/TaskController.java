package org.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.todolist.dto.TaskDTO;
import org.todolist.service.TaskService;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping()
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @GetMapping()
    public List<TaskDTO> allTasks() {
        return taskService.allTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskByID(@PathVariable Long id) {
        return taskService.getTaskByID(id);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public TaskDTO editList(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.editTask(taskDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
