package com.crud.tasks.mapper;

import com.crud.tasks.domain.TaskTrelloDto;
import com.crud.tasks.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskTrelloDto mapToTask(final TaskDto taskDto) {
        return new TaskTrelloDto(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent());
    }

    public TaskDto mapToTaskDto(TaskTrelloDto task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent());
    }

    public List<TaskDto> mapToTaskDtoList(final List<TaskTrelloDto> taskList) {
        return taskList.stream()
                .map(t ->new TaskDto(t.getId(), t.getTitle(), t.getContent()))
                .collect(Collectors.toList());
    }
}