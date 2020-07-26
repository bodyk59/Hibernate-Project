package com.softserve.academy.service;

import com.softserve.academy.entity.Progress;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Component
public interface ProgressService {
    Progress getProgressById(Long id);
    Progress addTaskForStudent(Task task, Users users);
    Progress addOrUpdateProgress(Progress progress);
    boolean setStatus(Task.TaskStatus task, Progress progress);
    List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId);
    List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId);
}
