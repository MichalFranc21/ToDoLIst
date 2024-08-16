package org.todolist.service;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.metamodel.mapping.EntityIdentifierMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todolist.dto.ListDTO;
import org.todolist.entity.ListEntity;
import org.todolist.entity.repository.ListRepository;
import org.todolist.mapper.ListMapper;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    ListRepository listRepository;
    @Autowired
    ListMapper listMapper;

    @Override
    public ListDTO createList(ListDTO listDTO) {
        ListEntity entity = listMapper.toEntity(listDTO);
        listRepository.save(entity);
        return listMapper.toDTO(entity);
    }

    @Override
    public List<ListDTO> allLists() {
        return listRepository.findAll()
                .stream()
                .map(listMapper ::toDTO)
                .toList();
    }

    @Override
    public ListDTO getListByID(Long id) {
        ListEntity entity = listRepository.getReferenceById(id);
        return listMapper.toDTO(entity);
    }

    @Override
    public ListDTO editList(ListDTO listDTO, Long id) {
        ListEntity entity = listMapper.toEntity(listDTO);
        entity.setId(id);
        listRepository.save(entity);
        return listMapper.toDTO(entity);
    }

    @Override
    public ListDTO deleteList(Long id) {
        ListEntity entity = listRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ListDTO dto = listMapper.toDTO(entity);
        listRepository.delete(entity);
        return dto;
    }
}
