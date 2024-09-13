package com.ceynetics.todo.controller;

import com.ceynetics.todo.dto.TodoRequest;
import com.ceynetics.todo.dto.TodoResponse;
import com.ceynetics.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<String> addTodo(@RequestBody TodoRequest todoRequest) {
        todoService.addTodo(todoRequest);
        return ResponseEntity.ok("Todo added successfully!");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable String id,@RequestBody TodoRequest todoRequest) {
        todoService.deleteTodo(id, todoRequest);
        return ResponseEntity.ok("Todo deleted successfully!");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable String id, @RequestBody TodoRequest todoRequest) {
        todoService.updateTodo(id, todoRequest);
        return ResponseEntity.ok("Todo updated successfully!");
    }

    @PostMapping("/all")
    public List<TodoResponse> getAllTodos(){
        return todoService.getAllTodos();
    }


    @PostMapping("/get/{id}")
    public TodoResponse getTodoById(@PathVariable String id){
        return todoService.getTodoById(id);
    }

    @PostMapping("/getByPriority/{priority}")
    public List<TodoResponse> getTodoByPriority(@PathVariable String priority){
        return todoService.getTodosByPriority(priority);
    }

}
