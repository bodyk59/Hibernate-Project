package com.softserve.academy.service.implementation;

import com.softserve.academy.exception.EntityNotFoundException;
import com.softserve.academy.model.Marathon;
import com.softserve.academy.model.Sprint;
import com.softserve.academy.model.Task;
import com.softserve.academy.model.Users;
import com.softserve.academy.repository.UserRepository;
import com.softserve.academy.service.UserService;
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
@Service("userServiceImplementation")
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<Users> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(BigInteger.valueOf(id)).orElse(null);
    }

    @Transactional
    @Override
    public Users createOrUpdateUser(Users user) {
        if (Objects.nonNull(user.getId())) {
            Users element = userRepository.findById(user.getId()).orElse(null);
            if (Objects.nonNull(element)) {
                element.setFirst_name(user.getFirst_name());
                element.setLast_name(user.getLast_name());
                element.setEmail(user.getEmail());
                element.setRole(user.getRole());
                element.setPassword(element.getPassword());
                return userRepository.save(element);
            } else {
                throw new EntityNotFoundException("Given User doesn`t exist");
            }
        }
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public List<Users> getAllByRole(String role) {
        if (Objects.nonNull(role)) {
            try {
                Users.Role newRole = Users.Role.valueOf(role);
                return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                        .filter(users -> Objects.equals(users.getRole(), newRole.toString()))
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                throw new EntityNotFoundException("Given Role doesn`t exist");
            }
        }
        return new ArrayList<>();
    }

    @Transactional
    @Override
    public boolean addUserToMarathon(Users user, Marathon marathon) {
        if (Objects.nonNull(user.getId()) && Objects.nonNull(marathon.getId())) {
            Users element = userRepository.findById(user.getId()).orElse(null);
            if (Objects.nonNull(element)) {
                element.getMarathons().add(marathon);
                userRepository.save(element);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean addUserToTask(Users user, Task task) {
        if (Objects.nonNull(user.getId()) && Objects.nonNull(task.getId())) {
            Users element = userRepository.findById(user.getId()).orElse(null);
            if (Objects.nonNull(element)) {
                for (Marathon marathon : element.getMarathons()) {
                    for (Sprint sprint : marathon.getSprint()) {
                        if (Objects.equals(sprint, task.getSprint())) {
                            sprint.getTask().add(task);
                            userRepository.save(element);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
