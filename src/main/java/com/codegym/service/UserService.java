package com.codegym.service;

import com.codegym.model.User;

public interface UserService {
    Iterable<User> findAll();

    void save(User user);
}
