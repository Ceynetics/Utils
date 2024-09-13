package com.ceynetics.todo.repository;

import com.ceynetics.todo.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo, Long> {

    Todo findById(String id);
    Todo findByTitle(String title);
    Todo findByPriority(String priority);
}
