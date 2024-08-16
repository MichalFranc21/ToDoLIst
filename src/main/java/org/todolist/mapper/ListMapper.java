package org.todolist.mapper;

import org.mapstruct.Mapper;
import org.todolist.dto.ListDTO;
import org.todolist.entity.ListEntity;

@Mapper(componentModel = "spring")
public interface ListMapper {
    ListDTO toDTO(ListEntity source);
    ListEntity toEntity(ListDTO source);
}