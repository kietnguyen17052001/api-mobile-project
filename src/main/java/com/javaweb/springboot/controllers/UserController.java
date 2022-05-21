package com.javaweb.springboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.springboot.dtos.UserDto;
import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{email}")
	public UserDto getUser(@PathVariable(name = "email") String email) {
		User user = service.getUser(email);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@PostMapping
	public void create(@RequestBody UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		service.create(user);
	}

	@PutMapping(value = "/{id}")
	public void udpate(@PathVariable(name = "id") int id, @RequestBody UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		service.update(id, user);
	}
}
