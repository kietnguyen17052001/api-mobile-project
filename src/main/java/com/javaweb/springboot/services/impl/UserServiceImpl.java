package com.javaweb.springboot.services.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User getUser(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public void create(User user) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		user.setCreatedAt(timestamp);
		user.setUpdatedAt(timestamp);
		repository.save(user);
	}

	@Override
	public void update(int id, User userRequest) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = repository.findOneById(id);
		user.setDisplayName(userRequest.getDisplayName());
		user.setUpdatedAt(timestamp);
		repository.save(user);
	}
}
