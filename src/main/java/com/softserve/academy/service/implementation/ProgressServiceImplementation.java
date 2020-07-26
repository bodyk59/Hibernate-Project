package com.softserve.academy.service.implementation;

import com.softserve.academy.exception.EntityNotFoundException;
import com.softserve.academy.model.*;
import com.softserve.academy.repository.ProgressRepository;
import com.softserve.academy.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Service("progressServiceImplementation")
public class ProgressServiceImplementation implements ProgressService {
    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressServiceImplementation(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Transactional
    @Override
    public Progress getProgressById(Long id) {
        return progressRepository.findById(BigInteger.valueOf(id)).orElse(null);
    }

    @Transactional
    @Override
    public Progress addTaskForStudent(Task task, Users users) {
        if (Objects.nonNull(task.getId()) && Objects.nonNull(users.getId())) {
            Progress element = progressRepository.findById(task.getProgress().getId()).orElse(null);
            if (Objects.nonNull(element)) {
                element.setUser(users);
                return progressRepository.save(element);
            }
        }
        throw new EntityNotFoundException("Given Task or User doesn`t exist");
    }

    @Transactional
    @Override
    public Progress addOrUpdateProgress(Progress progress) {
        if (Objects.nonNull(progress.getId())) {
            Progress element = progressRepository.findById(progress.getId()).orElse(null);
            if (Objects.nonNull(element)) {
                element.setStatus(progress.getStatus());
                element.setTask(progress.getTask());
                element.setStarted(progress.getStarted());
                element.setUpdated(progress.getUpdated());
                element.setUser(progress.getUser());
                return progressRepository.save(element);
            } else {
                throw new EntityNotFoundException("Given Progress doesn`t exist");
            }
        }
        return progressRepository.save(progress);
    }

    @Transactional
    @Override
    public boolean setStatus(Task.TaskStatus task, Progress progress) {
        if (Objects.nonNull(task) && Objects.nonNull(progress.getId())) {
            Progress element = progressRepository.findById(progress.getId()).orElse(null);
            if (Objects.nonNull(element)) {
                element.setStatus(task.toString());
                progressRepository.save(element);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
        if (Objects.nonNull(userId) && Objects.nonNull(marathonId)) {
            return StreamSupport.stream(progressRepository.findAll().spliterator(), false)
                    .filter(progress -> progress.getTrainee_id().longValue() == userId)
                    .filter(progress -> {
                        for (Marathon marathon : progress.getUser().getMarathons()) {
                            if (marathon.getId().longValue() == marathonId) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Transactional
    @Override
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        if (Objects.nonNull(userId) && Objects.nonNull(sprintId)) {
            return StreamSupport.stream(progressRepository.findAll().spliterator(), false)
                    .filter(progress -> progress.getTrainee_id().longValue() == userId)
                    .filter(progress -> {
                        for (Marathon marathon : progress.getUser().getMarathons()) {
                            for (Sprint sprint : marathon.getSprint()) {
                                if (sprint.getId().longValue() == sprintId) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
