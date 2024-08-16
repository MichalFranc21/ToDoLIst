package org.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.todolist.dto.PriorityEnum;
import java.util.List;

@Entity(name = "Tasks")
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "lists_of_tasks",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<ListEntity> lists;
    private boolean hidden = false;
}
