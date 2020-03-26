package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldFetchAllTasks() {
        //Given
        Task task1 = new Task(1L, "Task1", "content1");
        Task task2 = new Task(2L, "Task2", "content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(repository.findAll()).thenReturn(tasks);
        //When
        List allTasks = dbService.getAllTasks();
        //Then
        assertEquals(2, allTasks.size());
    }

    @Test
    public void shouldFetchEmptyTaskList() {
        //Given
        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        //When
        List allTasks = dbService.getAllTasks();
        //Then
        assertEquals(0, allTasks.size());
    }

    @Test
    public void shouldFetchTask() {
        //Given
        Task task1 = new Task(1L, "Task1", "content1");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);

        when(repository.findById(1L)).thenReturn(Optional.of(task1));
        //When
        Task result = dbService.getTask(1L).get();
        //Then
        assertEquals(1L, result.getId(), 0);
        assertEquals("Task1", result.getTitle());
        assertEquals("content1", result.getContent());
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task1 = new Task(1L, "Task1", "content1");
        Task updatedTask = new Task(1L, "Updated Tasks1", "content1");
        when(repository.save(task1)).thenReturn(updatedTask);
        //When
        Task result = dbService.saveTask(task1);
        //Then
        assertEquals(1L, result.getId(), 0);
        assertEquals("Updated Tasks1", result.getTitle());
        assertEquals("content1", result.getContent());
    }

    @Test
    public void shouldDeleteTask() {

        //When
        dbService.deleteTask(1L);
        //Then
        Mockito.verify(repository, times(1)).deleteById(1L);
    }
}