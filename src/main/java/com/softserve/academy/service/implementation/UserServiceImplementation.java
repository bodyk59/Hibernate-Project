package com.softserve.academy.service.implementation;

import com.softserve.academy.entity.Marathon;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.Users;
import com.softserve.academy.service.UserService;

import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public class UserServiceImplementation implements UserService {
    @Override
    public List<Users> getAll() {
        return null;
    }

    @Override
    public Users getUserById(Long id) {
        return null;
    }

    @Override
    public Users createOrUpdateUser(Users user) {
        return null;
    }

    @Override
    public List<Users> getAllByRole(String role) {
        return null;
    }

    @Override
    public boolean addUserToMarathon(Users user, Marathon marathon) {
        return false;
    }

    @Override
    public boolean addUserToTask(Users user, Task task) {
        return false;
    }
}
