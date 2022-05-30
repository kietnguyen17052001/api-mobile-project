package com.javaweb.springboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.springboot.dtos.UserDto;
import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{email}")
	public UserDto getUserByEmail(@PathVariable(name = "email") String email) {
		User user = service.getUserByEmail(email);
		if (user == null) {
			return null;
		}
		return modelMapper.map(user, UserDto.class);
	}

	@GetMapping
	public UserDto getUserByUsernameAndPassword(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		User user = service.getUserByUsernameAndPassword(username, password);
		if (user == null) {
			return null;
		}
		return modelMapper.map(user, UserDto.class);
	}

	@PostMapping
	public UserDto create(@RequestParam(value = "loginType") int loginTypeId, @RequestBody UserDto userDtoRequest) {
		User user = modelMapper.map(userDtoRequest, User.class);
		return modelMapper.map(service.create(user, loginTypeId), UserDto.class);
	}

	@PutMapping(value = "/{id}")
	public UserDto udpate(@PathVariable(name = "id") int id, @RequestBody UserDto userDtoRequest) {
		User user = modelMapper.map(userDtoRequest, User.class);
		return modelMapper.map(service.update(id, user), UserDto.class);
	}
}
