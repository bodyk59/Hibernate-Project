package com.softserve.academy.service.implementation;

import com.softserve.academy.entity.Marathon;
import com.softserve.academy.entity.Sprint;
import com.softserve.academy.service.SprintService;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public class SprintServiceImplementation implements SprintService {

    @Override
    public List<Sprint> getSprintsByMarathonId(Long marathonId) {
        return null;
    }

    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        return false;
    }

    @Override
    public boolean updateSprint(Sprint sprint) {
        return false;
    }

    @Override
    public Sprint getSprintById(Long id) {
        return null;
    }
}
