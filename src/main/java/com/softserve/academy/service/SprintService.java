package com.softserve.academy.service;

import com.softserve.academy.model.Marathon;
import com.softserve.academy.model.Sprint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Component
public interface SprintService {
    List<Sprint> getSprintsByMarathonId(Long marathonId);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    boolean updateSprint(Sprint sprint);
    Sprint getSprintById(Long id);
}
