package com.softserve.academy.service.implementation;

import com.softserve.academy.entity.Marathon;
import com.softserve.academy.repository.MarathonRepository;
import com.softserve.academy.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Component
public class MarathonServiceImplementation implements MarathonService {
    private MarathonRepository marathonRepository;

    @Autowired
    public MarathonServiceImplementation(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Override
    public List<Marathon> getAll() {
        return null;
    }

    @Override
    public Marathon getMarathonById(Long id) {
        return null;
    }

    @Override
    public Marathon createOrUpdate(Marathon marathon) {
        return null;
    }

    @Override
    public void deleteMarathonById(Long id) {

    }
}
