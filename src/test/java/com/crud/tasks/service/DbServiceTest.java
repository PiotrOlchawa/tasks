package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasks() {

        //Given
        Task task1 = new Task(1L, "test", "my_test");
        List<Task> taskList = Arrays.asList(task1);

        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        List<Task> allTasks = dbService.getAllTasks();

        //Then
        Assert.assertEquals(1, allTasks.size());

    }

    @Test
    public void getTask() {
        //Given
        Task task1 = new Task(1L, "test", "my_test");
        Optional<Task> taskOptional = Optional.ofNullable(task1);
        when(taskRepository.findById(anyLong())).thenReturn(taskOptional);

        //When
        Task result = dbService.getTask(1L).orElse(new Task(1L, "enpty", "enpty"));
        long id = result.getId();
        //Then
        Assert.assertEquals(1L, id);

    }

}
