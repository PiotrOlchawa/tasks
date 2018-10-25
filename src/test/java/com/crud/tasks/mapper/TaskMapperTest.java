package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
        private TaskMapper taskMapper;

        @Test
        public void mapToTaskDtoTest (){
            //given
            Task task = new Task((long) 1, "Task","Task" );
            //when
            TaskDto taskDto = taskMapper.mapToTaskDto(task);
            //then
            Assert.assertEquals(1l,taskDto.getId().longValue());
        }
        @Test
        public void mapToTaskTest (){
            //given
            TaskDto taskDto = new TaskDto((long) 1, "Task","Task" );
            //when
            Task task = taskMapper.mapToTask(taskDto);
            //then
            Assert.assertEquals(1,task.getId().longValue());
        }
        @Test
        public void mapToTaskDtoListTest(){
            //given
            Task task = new Task((long) 1, "Task","Task" );
            List<Task> tasks = new ArrayList<>();
            tasks.add(task);
            //when
            List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
            //then
            Assert.assertEquals(1,taskDtoList.size());
        }

    }
