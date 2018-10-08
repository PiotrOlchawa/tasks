package com.crud.tasks.repository;

import com.crud.tasks.domain.TaskTrelloDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<TaskTrelloDto, Long> {

    @Override
    List<TaskTrelloDto> findAll();

    @Override
    TaskTrelloDto save(TaskTrelloDto task);

    Optional<TaskTrelloDto> findById(Long id);

    void deleteById(Long id);

    @Override
    long count();

}