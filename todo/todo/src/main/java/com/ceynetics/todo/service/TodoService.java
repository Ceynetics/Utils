package com.ceynetics.todo.service;

import com.ceynetics.todo.dto.TodoRequest;
import com.ceynetics.todo.dto.TodoResponse;
import com.ceynetics.todo.model.Todo;
import com.ceynetics.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;
    private final WebClient webClient;

    public void addTodo(TodoRequest todoRequest) {

        //call user service to get user details
        Todo todo = Todo.builder()
                .title(todoRequest.getTitle())
                .description(todoRequest.getDescription())
                .completed(todoRequest.isCompleted())
                .priority(todoRequest.getPriority())
                .build();

        // Save the todo to the database
        todoRepository.save(todo);
        log.info("Todo:{} added successfully", todo.getTitle());
    }

    public void deleteTodo(String id, TodoRequest todoRequest) {
        // Delete a todo
        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            log.error("Todo:{} not found", todoRequest.getTitle());
            return;
        }

        todoRepository.delete(todo);
        log.info("Todo:{} deleted successfully", todo.getTitle());
    }

    public void updateTodo(String id, TodoRequest todoRequest) {
        // Update a todo

        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            log.error("Todo:{} not found!", todoRequest.getTitle());
            return;
        }

        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todoRequest.isCompleted());
        todo.setPriority(todoRequest.getPriority());

        todoRepository.save(todo);
        log.info("Todo:{} updated successfully", todo.getTitle());
    }

    public List<TodoResponse> getAllTodos(){

        List<Todo> todos = todoRepository.findAll();
        List<TodoResponse> todoResponses = new ArrayList<>();

        for (Todo todo : todos) {
            todoResponses.add(TodoResponse.builder()
                    .title(todo.getTitle())
                    .description(todo.getDescription())
                    .completed(todo.isCompleted())
                    .priority(todo.getPriority())
                    .build());
        }
        return todoResponses;
    }


    public TodoResponse getTodoById(String id){
        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            log.error("Todo:{} is not found.", id);
            return null;
        }
        return TodoResponse.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .priority(todo.getPriority())
                .build();
    }

    public List<TodoResponse> getTodosByPriority(String priority){
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponse> todoResponses = new ArrayList<>();

        for (Todo todo : todos) {
            if (todo.getPriority().equals(priority)) {
                todoResponses.add(TodoResponse.builder()
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .completed(todo.isCompleted())
                        .priority(todo.getPriority())
                        .build());
            }
        }

        return todoResponses;
    }
}


