package com.softserve.academy.service.implementation;

import com.softserve.academy.exception.EntityNotFoundException;
import com.softserve.academy.model.Sprint;
import com.softserve.academy.model.Task;
import com.softserve.academy.repository.TaskRepository;
import com.softserve.academy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Service("taskServiceImplementation")
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Override
    public Task addTaskToSprint(Task task, Sprint sprint) {
        if (Objects.nonNull(task.getId()) && Objects.nonNull(sprint.getId())) {
            Task element = taskRepository.findById(task.getId()).orElse(null);
            if (Objects.nonNull(element) && !Objects.equals(element.getSprint(), sprint)) {
                element.setSprint(sprint);
                return taskRepository.save(element);
            } else {
                throw new EntityNotFoundException("Given Task not found or the Task already assign to sprint");
            }
        } else {
            throw new EntityNotFoundException("Some elements are null");
        }
    }

    @Transactional
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(BigInteger.valueOf(id)).orElse(null);
    }
}
