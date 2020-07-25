package com.softserve.academy.service;

import com.softserve.academy.entity.Progress;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.Users;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public interface ProgressService {
    Progress getProgressById(Long id);
    Progress addTaskForStudent(Task task, Users users);
    Progress addOrUpdateProgress(Progress progress);
    // TODO: in the specification the first parameter was TaskStatus -> why?
    boolean setStatus(Task task);
    List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId);
    List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId);
}
