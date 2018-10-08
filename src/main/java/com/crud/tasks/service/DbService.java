package com.crud.tasks.service;

import com.crud.tasks.domain.TaskTrelloDto;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<TaskTrelloDto> getAllTasks() {
        return repository.findAll();
    }

    public Optional<TaskTrelloDto> getTask(final long id) {
        return repository.findById(id);

    }

    public TaskTrelloDto saveTask(final TaskTrelloDto task){
        return repository.save(task);
    }

    public void deleteTask(final long id) {
       repository.deleteById(id);
    }

}