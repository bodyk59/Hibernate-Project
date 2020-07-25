package com.softserve.academy.service;

import com.softserve.academy.entity.Marathon;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public interface MarathonService {
    List<Marathon> getAll();
    Marathon getMarathonById(Long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(Long id);
}
