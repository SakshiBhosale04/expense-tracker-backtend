package com.financetracker.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financetracker.entity.User;
import com.financetracker.repo.UserRepository;
import com.financetracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public User login(User loginRequest) {
	    User user = userRepository.findByEmail(loginRequest.getEmail())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!user.getPassword().equals(loginRequest.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }

	    return user;
	}
}
