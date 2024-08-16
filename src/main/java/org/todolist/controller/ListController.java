package org.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.todolist.dto.ListDTO;
import org.todolist.service.ListService;
import java.util.List;

@RestController
@RequestMapping({"/api/lists", "/api/lists/"})
public class ListController {

    @Autowired
    ListService toDoListService;

    @PostMapping()
    public ListDTO createList(@RequestBody ListDTO toDoListDTO) {
        return toDoListService.createList(toDoListDTO);
    }

    @GetMapping()
    public List<ListDTO> allLists() {
        return toDoListService.allLists();
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ListDTO getListByID(@PathVariable Long id) {
        return toDoListService.getListByID(id);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ListDTO editList(@PathVariable Long id, @RequestBody ListDTO toDoListDTO) {
        return toDoListService.editList(toDoListDTO, id);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public void deleteList(@PathVariable Long id) {
        toDoListService.deleteList(id);
    }
}
