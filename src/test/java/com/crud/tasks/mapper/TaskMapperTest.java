package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskMapperTest {
    @Test
    public void MapToTaskTest(){
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L,"test_title","test_content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(),task.getId());
        assertEquals(taskDto.getContent(), task.getContent());
        assertEquals(taskDto.getTitle(),task.getTitle());
    }

    @Test
    public void MapToTaskDtoTest(){
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L,"test_title","test_content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getContent(),taskDto.getContent());
        assertEquals(task.getId(),taskDto.getId());
        assertEquals(task.getTitle(),taskDto.getTitle());
    }

    @Test
    public void MapToTaskDtoListTest(){
        //Given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L,"test_title","test_content");
        taskList.add(task);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(taskList.get(0).getTitle(),taskDtoList.get(0).getTitle());
        assertEquals(taskList.get(0).getId(),taskDtoList.get(0).getId());
        assertEquals(taskList.get(0).getContent(),taskDtoList.get(0).getContent());
    }
}