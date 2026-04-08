package com.financetracker.service;

import java.util.List;

import com.financetracker.entity.User;

public interface AuthService {

	User register(User user);

	User login(String email, String password);

	List<User> saveAllUsers(List<User> users);
}