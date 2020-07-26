package com.softserve.academy.service;

import com.softserve.academy.entity.Marathon;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Component
public interface MarathonService {
    List<Marathon> getAll();
    Marathon getMarathonById(Long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(Long id);
}
