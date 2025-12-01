package com.example.demo.controllers;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todo")
    public List<Todo> getTodos(@RequestParam(required = false) String status) {
        return todoRepository.findTodos(status);
    }

    @GetMapping("/todo/{id}")
    public Todo getTodosById(@PathVariable Integer id) {
        return todoRepository.findTodosById(id);
    }

    @PostMapping("/todo")
    public String postTodo(@RequestBody Todo todo){
        return todoRepository.createTodo(todo);
    }
}
