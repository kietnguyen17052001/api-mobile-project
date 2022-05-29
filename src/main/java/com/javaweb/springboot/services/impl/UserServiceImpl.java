package com.javaweb.springboot.services.impl;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		return repository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User getUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User create(User user) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
		user.setCreatedAt(timestamp);
		user.setUpdatedAt(timestamp);
		repository.save(user);
		return user;
	}

	@Override
	public User update(int id, User userRequest) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = repository.findOneById(id);
		user.setDisplayName(userRequest.getDisplayName());
		user.setUpdatedAt(timestamp);
		repository.save(user);
		return user;
	}

}
