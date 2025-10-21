package com.example.todolist.service;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository)
    {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo todo)
    {
        return todoRepository.save(todo);
    }

    public List<Todo> getAll()
    {
        return todoRepository.findAll();
    }

    public Todo getById(Long id)
    {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo update(Long id, Todo updatedTodo) {
        return todoRepository.findById(id).map(todo -> {
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todo.setDeadline(updatedTodo.getDeadline());
            return todoRepository.save(todo);
        }).orElse(null);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
