package org.todolist.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todolist.dto.TaskDTO;
import org.todolist.entity.ListEntity;
import org.todolist.entity.TaskEntity;
import org.todolist.entity.repository.ListRepository;
import org.todolist.entity.repository.TaskRepository;
import org.todolist.mapper.TaskMapper;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ListRepository listRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.listRepository = listRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        TaskEntity entity = taskMapper.toEntity(taskDTO);
        if (entity.getLists() == null) {
            entity.setLists(new ArrayList<>());
        }
        // Čistíme seznamy, aby nedošlo k duplicitám
        entity.getLists().clear();
        for (Long listID : taskDTO.getListIDs()) {
            ListEntity list = listRepository.findById(listID)
                    .orElseThrow(() -> new EntityNotFoundException("List not found"));
            entity.getLists().add(list);
        }
        TaskEntity saved = taskRepository.save(entity);
        return taskMapper.toDTO(saved);
    }

    @Override
    public List<TaskDTO> allTasks() {
        List<TaskDTO> result = new ArrayList<>();
        taskRepository.findAll().forEach(task ->
                result.add(taskMapper.toDTO(task))
        );
        return result;
    }

    @Override
    public TaskDTO getTaskByID(Long id) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return taskMapper.toDTO(entity);
    }

    @Override
    public void deleteTask(Long id) {
        try {
            TaskEntity entity = fetchTaskByID(id);
            entity.setHidden(true);
            taskRepository.save(entity);
        } catch (NotFoundException ignored) {
        }
    }

    @Override
    public TaskDTO editTask(TaskDTO taskDTO, Long id) {
        TaskEntity entity = taskMapper.toEntity(taskDTO);
        entity.setId(id);
        if (entity.getLists() == null) {
            entity.setLists(new ArrayList<>());
        }
        // Čistíme seznamy, aby nedošlo k duplicitám
        entity.getLists().clear();
        for (Long listID : taskDTO.getListIDs()) {
            ListEntity list = listRepository.findById(listID)
                    .orElseThrow(() -> new EntityNotFoundException("List not found"));
            entity.getLists().add(list);
        }
        taskRepository.save(entity);
        return taskMapper.toDTO(entity);
    }

    private TaskEntity fetchTaskByID(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " wasn't found in the database."));
    }
}
