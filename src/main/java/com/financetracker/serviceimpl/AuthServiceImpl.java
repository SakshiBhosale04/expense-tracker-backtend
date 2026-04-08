package com.financetracker.serviceimpl;

import com.financetracker.entity.User;
import com.financetracker.repo.UserRepository;
import com.financetracker.service.AuthService;
import com.financetracker.config.SimpleEncoder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	private SimpleEncoder passwordEncoder = new SimpleEncoder();

	@Override
	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User login(String email, String password) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return user;
	}

	@Override
	public List<User> saveAllUsers(List<User> users) {

		// Encode password for each user
		for (User user : users) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		// Save all users in DB
		return userRepository.saveAll(users);
	}
}