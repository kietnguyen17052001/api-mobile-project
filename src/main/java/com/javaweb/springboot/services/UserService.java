package com.javaweb.springboot.services;

import com.javaweb.springboot.entities.User;

public interface UserService {
	User getUserByUsernameAndPassword(String username, String password);

	User getUserByEmail(String email);

	User getUserByUID(String uid);

	User create(User user, int loginTypeId);

	User update(int id, User user);
}
