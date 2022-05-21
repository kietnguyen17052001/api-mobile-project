package com.javaweb.springboot.services;

import com.javaweb.springboot.entities.User;

public interface UserService {
	User getUser(String email);

	void create(User user);

	void update(int id, User user);
}
