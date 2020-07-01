package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(final long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }
    public Task deleteTask(final long taskId) {
        return taskRepository.deleteById(taskId);
    }
}
