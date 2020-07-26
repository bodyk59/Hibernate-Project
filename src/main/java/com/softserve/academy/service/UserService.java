package com.softserve.academy.service;

import com.softserve.academy.entity.Marathon;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Component
public interface UserService {
    List<Users> getAll();
    Users getUserById(Long id);
    Users createOrUpdateUser(Users user);
    List<Users> getAllByRole(String role);
    boolean addUserToMarathon(Users user, Marathon marathon);
    boolean addUserToTask(Users user, Task task);
}
