package org.todolist.service;

import org.todolist.dto.ListDTO;
import java.util.List;

public interface ListService {
    ListDTO createList(ListDTO listDTO);
    List<ListDTO> allLists();
    ListDTO getListByID(Long id);
    ListDTO editList(ListDTO listDTO, Long id);
    ListDTO deleteList(Long id);
}
