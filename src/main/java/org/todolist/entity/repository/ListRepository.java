package org.todolist.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todolist.entity.ListEntity;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {
}
