package org.todolist.service;

import org.todolist.dto.TaskDTO;
import java.util.List;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> allTasks();
    TaskDTO getTaskByID(Long id);
    void deleteTask(Long id);
    TaskDTO editTask(TaskDTO taskDTO, Long id);
}
