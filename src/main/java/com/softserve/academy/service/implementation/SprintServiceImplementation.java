package com.softserve.academy.service.implementation;

import com.softserve.academy.model.Marathon;
import com.softserve.academy.model.Sprint;
import com.softserve.academy.repository.SprintRepository;
import com.softserve.academy.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Service("sprintServiceImplementation")
public class SprintServiceImplementation implements SprintService {
    private final SprintRepository sprintRepository;

    @Autowired
    public SprintServiceImplementation(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Transactional
    @Override
    public List<Sprint> getSprintsByMarathonId(Long marathonId) {
        return StreamSupport.stream(sprintRepository.findAll().spliterator(), false)
                .filter(sprint -> sprint.getMarathon().getId().longValue() == marathonId)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        if (Objects.nonNull(sprint.getId()) && Objects.nonNull(marathon.getId())) {
            Sprint element = sprintRepository.findById(sprint.getId()).orElse(null);
            if (Objects.nonNull(element) && !Objects.equals(element.getMarathon(), marathon)) {
                sprint.setMarathon(marathon);
                sprintRepository.save(sprint);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateSprint(Sprint sprint) {
        if (Objects.nonNull(sprint.getId())) {
            Sprint element = sprintRepository.findById(sprint.getId()).orElse(null);
            if (Objects.nonNull(element)) {
                element.setTitle(sprint.getTitle());
                element.setMarathon(sprint.getMarathon());
                element.setStart_date(sprint.getStart_date());
                element.setFinish(sprint.getFinish());
                sprintRepository.save(element);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(BigInteger.valueOf(id)).orElse(null);
    }
}
