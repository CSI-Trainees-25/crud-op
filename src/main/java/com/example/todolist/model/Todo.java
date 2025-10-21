package com.example.todolist.model;

import jakarta.persistence.*;

import java.lang.annotation.Documented;
import java.time.LocalDate;

@Entity

@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private LocalDate deadline;
    private LocalDate createdAt;

    @PrePersist
    public void onCreate()
    {
        this.createdAt = LocalDate.now();
    }


    public Long getId()
    {
        return id;

    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getDeadline()
    {
        return deadline;
    }
    public void setDeadline(LocalDate deadline)
    {
        this.deadline = deadline;
    }

    public LocalDate getCreatedAt()
    {
        return createdAt;
    }
}
