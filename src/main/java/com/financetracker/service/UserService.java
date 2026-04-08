package com.financetracker.service;

import com.financetracker.entity.User;

public interface UserService {
    User register(User user);
    User getUserById(Long id);
    User login(User loginRequest);

}