package com.softserve.academy.service.implementation;

import com.softserve.academy.model.Marathon;
import com.softserve.academy.repository.MarathonRepository;
import com.softserve.academy.service.MarathonService;
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
@Service("marathonServiceImplementation")
public class MarathonServiceImplementation implements MarathonService {
    private final MarathonRepository marathonRepository;

    @Autowired
    public MarathonServiceImplementation(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Transactional
    @Override
    public List<Marathon> getAll() {
        return StreamSupport.stream(marathonRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Marathon getMarathonById(Long id) {
        return marathonRepository.findById(BigInteger.valueOf(id)).orElse(null);
    }

    @Transactional
    @Override
    public Marathon createOrUpdate(Marathon marathon) {
        if (Objects.nonNull(marathon.getId())) {
            Marathon element = getMarathonById(marathon.getId().longValue());
            if (Objects.nonNull(element)) {
                element.setTitle(marathon.getTitle());
                return marathonRepository.save(element);
            }
            //TODO: Throw exception, because the id already exist, but the marathon doesn`t
        }
       return marathonRepository.save(marathon);
    }

    @Transactional
    @Override
    public void deleteMarathonById(Long id) {
        if (Objects.nonNull(marathonRepository.findById(BigInteger.valueOf(id)))) {
            marathonRepository.deleteById(BigInteger.valueOf(id));
            return;
        }
        //TODO: Throw exception, because there isn`t needed marathon
    }
}
