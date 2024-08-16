package org.todolist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.todolist.dto.TaskDTO;
import org.todolist.entity.ListEntity;
import org.todolist.entity.TaskEntity;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "listIDs", expression = "java(getListIDs(source))")
    TaskDTO toDTO(TaskEntity source);
    TaskEntity toEntity(TaskDTO source);

    default List<Long> getListIDs(TaskEntity source) {
        List<Long> result = new ArrayList<>();
        for (ListEntity list : source.getLists()) {
            result.add(list.getId());
        }
        return result;
    }
}
