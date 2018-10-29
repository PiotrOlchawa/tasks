package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskDtoTest() {
        //given
        Task task = new Task((long) 1, "Task", "Task");
        //when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //then
        assertEquals(1l, taskDto.getId().longValue());
    }

    @Test
    public void mapToTaskTest() {
        //given
        TaskDto taskDto = new TaskDto((long) 1, "Task", "Task");
        //when
        Task task = taskMapper.mapToTask(taskDto);
        //then
        assertEquals(1, task.getId().longValue());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //given
        Task task = new Task((long) 1, "Task", "Task");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //then
        assertEquals(1, taskDtoList.size());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = Arrays.asList(
                new Task(1L, "title1", "content4"),
                new Task(2L, "title2", "content5"),
                new Task(3L, "title3", "content6")
        );

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        TaskDto taskDto1 = taskDtoList.get(0);
        TaskDto taskDto2 = taskDtoList.get(1);
        TaskDto taskDto3 = taskDtoList.get(2);

        //Then
        assertEquals(3, taskDtoList.size());

        assertEquals("title1", taskDto1.getTitle());
        assertEquals("title2", taskDto2.getTitle());
        assertEquals("title3", taskDto3.getTitle());

        assertEquals("content4", taskDto1.getContent());
        assertEquals("content5", taskDto2.getContent());
        assertEquals("content6", taskDto3.getContent());
    }


}
