package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.taskmapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DbService dbService;

    @MockBean
    TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1l,"task test","to testing..."));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1l,"task test","to testing..."));
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("task test")))
                .andExpect(jsonPath("$[0].content",is("to testing...")));
        verify(dbService,times(1)).getAllTasks();
        verify(taskMapper,times(1)).mapToTaskDtoList(anyList());
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1l,"task test","to testing...");
        TaskDto taskDto = new TaskDto(1l,"task test","to testing...");
        when(dbService.getTask(anyLong())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON).param("taskId","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("task test")))
                .andExpect(jsonPath("$.content",is("to testing...")));
        verify(dbService,times(1)).getTask(anyLong());
        verify(taskMapper,times(1)).mapToTaskDto(any(Task.class));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1"))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(anyLong());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task taskToUpdate = new Task(1l,"task test after update","to testing...update");
        TaskDto taskDtoToUpdate = new TaskDto(1l,"task test after update","to testing...update");
        Gson gson = new Gson();
        String jsonTaskDto = gson.toJson(taskToUpdate);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(taskToUpdate);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDtoToUpdate);
        when(dbService.saveTask(any(Task.class))).thenReturn(taskToUpdate);
        //When & Given
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonTaskDto))
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id",is(1)),
                        jsonPath("$.title",is("task test after update")),
                        jsonPath("$.content",is("to testing...update"))));
        verify(dbService,times(1)).saveTask(any(Task.class));
        verify(taskMapper,times(1)).mapToTaskDto(any(Task.class));
        verify(taskMapper,times(1)).mapToTask(any(TaskDto.class));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task taskToCreate = new Task(1l,"task test to create","to testing...create");
        Gson gson = new Gson();
        String jsonTaskDtoToUpdate = gson.toJson(taskToCreate);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(taskToCreate);
        when(dbService.saveTask(any(Task.class))).thenReturn(taskToCreate);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonTaskDtoToUpdate))
        .andExpect(status().isOk());
        verify(dbService,times(1)).saveTask(any(Task.class));
        verify(taskMapper,times(1)).mapToTask(any(TaskDto.class));
    }
}