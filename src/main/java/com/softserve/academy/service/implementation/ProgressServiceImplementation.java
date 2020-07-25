package com.softserve.academy.service.implementation;

import com.softserve.academy.entity.Progress;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.Users;
import com.softserve.academy.service.ProgressService;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public class ProgressServiceImplementation implements ProgressService {

    @Override
    public Progress getProgressById(Long id) {
        return null;
    }

    @Override
    public Progress addTaskForStudent(Task task, Users users) {
        return null;
    }

    @Override
    public Progress addOrUpdateProgress(Progress progress) {
        return null;
    }

    @Override
    public boolean setStatus(Task task) {
        return false;
    }

    @Override
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
        return null;
    }

    @Override
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        return null;
    }
}
