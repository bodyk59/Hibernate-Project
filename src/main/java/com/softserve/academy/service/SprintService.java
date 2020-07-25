package com.softserve.academy.service;

import com.softserve.academy.entity.Marathon;
import com.softserve.academy.entity.Sprint;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public interface SprintService {
    List<Sprint> getSprintsByMarathonId(Long marathonId);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    boolean updateSprint(Sprint sprint);
    Sprint getSprintById(Long id);
}
