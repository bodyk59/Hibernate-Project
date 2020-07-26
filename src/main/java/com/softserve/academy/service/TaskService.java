package com.softserve.academy.service;

import com.softserve.academy.model.Sprint;
import com.softserve.academy.model.Task;
import org.springframework.stereotype.Component;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Component
public interface TaskService {
    Task addTaskToSprint(Task task, Sprint sprint);
    Task getTaskById(Long id);
}
