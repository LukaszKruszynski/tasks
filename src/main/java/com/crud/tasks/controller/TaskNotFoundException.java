package com.crud.tasks.controller;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(Long taskId) {
        super(String.format("Task with given ID: [%d] not found" , taskId));
    }
}

