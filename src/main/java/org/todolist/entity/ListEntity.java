package org.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity(name = "lists")
@Getter
@Setter
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lists")
    private List<TaskEntity> tasks;
}
