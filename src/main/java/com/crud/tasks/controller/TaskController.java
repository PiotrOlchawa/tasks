package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class TaskController {

/*
    private TaskMapper taskMapper;
    private DbService dbService;
*/

    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    public TaskDto getTask() {
        return new TaskDto((long) 1, "Title", "Content");
    }

    public void deleteTask(Long taskId) {
    }

    public TaskDto updateTask(TaskDto task) {
        return new TaskDto(1L, "Title updated", "Content updated");
    }

    public void createTask(TaskDto task) {
    }

}
