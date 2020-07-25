package com.softserve.academy.service;

import com.softserve.academy.entity.Sprint;
import com.softserve.academy.entity.Task;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public interface TaskService {
    Task addTaskToSprint(Task task, Sprint sprint);
    Task getTaskById(Long id);
}
