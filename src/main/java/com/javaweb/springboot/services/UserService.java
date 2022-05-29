package com.javaweb.springboot.services;

import com.javaweb.springboot.entities.User;

public interface UserService {
	User getUserByUsernameAndPassword(String username, String password);
	
	User getUserByEmail(String email);
	
	User create(User user);

	User update(int id, User user);
}
