package com.accolite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.model.User;
import com.accolite.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public List<User> getUserByAddress(String address) {
		return userRepository.findByAddress(address);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
		
	}

}
