package com.crud.tasks.controller;

import com.crud.tasks.domain.*;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService dbService;

    @Test
    public void getEmptyTaskList() throws Exception {
        //given
        List<TaskDto> taskDtos = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos);
        //when&then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getTaskList() throws Exception {
        //given
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "test", "test");
        taskDtos.add(taskDto);

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos);
        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].content", is("test")));
    }

    @Test
    public void getTaskTest() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L, "test", "test");
        when(taskMapper.mapToTaskDto(dbService.getTask(1).orElse(new Task(1L, "enpty Task", "enpty Task")))).thenReturn(taskDto);
        //when&Then
        mockMvc.perform(get("/v1/task/getTask/?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void updateTaskTest() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L, "test", "test");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(Matchers.any(TaskDto.class))))).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //when&then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void createTaskTest() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L, "test", "test");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //when&then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        Task task = new Task(1L, "test11", "testing111");

        doNothing().when(dbService).deleteTask(task.getId());
        mockMvc.perform(delete("/v1/tasks/1")
                .param("taskId", "1"))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(task.getId());
        verifyNoMoreInteractions(dbService);
    }

    @Test
    public void shouldUpdatetask() throws Exception {
        //given
        Task task = new Task(1L, "Task", "Test_1");
        TaskDto taskDto = new TaskDto(1L, "Task", "Test_1");


        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks", 1L).contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1")
                .characterEncoding("UTF-8")
                .content(jsonContent)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task")))
                .andExpect(jsonPath("$.content", is("Test_1")));
        verify(taskMapper, times(1)).mapToTaskDto(any(Task.class));
        verify(taskMapper, times(1)).mapToTask(any(TaskDto.class));
        verify(dbService, times(1)).saveTask(task);
        verifyNoMoreInteractions(taskMapper);
        verifyNoMoreInteractions(dbService);
    }

}