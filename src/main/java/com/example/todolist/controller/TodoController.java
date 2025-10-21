package com.example.todolist.controller;

import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    public TodoController(TodoService todoService)
    {
        this.todoService = todoService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> create(@RequestBody Todo todo)
    {
        logger.info("Create TODO request received: {}", todo);
        return ResponseEntity.ok(todoService.create(todo));

    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAll()
    {
        return ResponseEntity.ok(todoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id)
    {
        Todo todo = todoService.getById(id);
        return todo != null ? ResponseEntity.ok(todo) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo updatedTodo)
    {
        Todo todo = todoService.update(id, updatedTodo);
        return todo != null ? ResponseEntity.ok(todo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
