package com.javaweb.springboot.services.impl;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.repositories.LoginTypeRepository;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private LoginTypeRepository loginTypeRepository;

	public String hashPassword(String password) {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, hashPassword(password));
	}

	@Override
	public User getUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User create(User user, int loginTypeId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		user.setPassword(hashPassword(user.getPassword()));
		user.setCreatedAt(timestamp);
		user.setUpdatedAt(timestamp);
		user.setLoginType(loginTypeRepository.findOneById(loginTypeId));
		repository.save(user);
		return user;
	}

	@Override
	public User update(int id, User userRequest) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = repository.findOneById(id);
		user.setUpdatedAt(timestamp);
		if (!user.getDisplayName().equals(userRequest.getDisplayName())) {
			user.setDisplayName(userRequest.getDisplayName());
		}
		if (!user.getPassword().equals(hashPassword(userRequest.getPassword()))) {
			user.setPassword(hashPassword(userRequest.getPassword()));
		}
		repository.save(user);
		return user;
	}

}
