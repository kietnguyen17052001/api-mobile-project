package com.javaweb.springboot.services.impl;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import com.google.common.hash.Hashing;
import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.repositories.LoginTypeRepository;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private static final int ACCOUNT = 2;
	@Autowired
	private UserRepository repository;
	@Autowired
	private LoginTypeRepository loginTypeRepository;

	public String hashPassword(String password) {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	}

	// Login by account
	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, hashPassword(password));
	}

	// Login by google
	@Override
	public User getUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	// Login by facebook
	@Override
	public User getUserByUID(String uid) {
		return repository.findByUsername(uid);
	}

	@Override
	public User getUserByID(int id) {
		return repository.findOneById(id);
	}

	@Override
	public User create(User user, int loginTypeId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (loginTypeId == ACCOUNT) {
			user.setPassword(hashPassword(user.getPassword()));
		}
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
