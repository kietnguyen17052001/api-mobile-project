package com.javaweb.springboot.services;

import com.javaweb.springboot.entities.User;

public interface UserService {

	User getUser(String email);

	User create(User user);

	User update(int id, User user);
}
