package com.javaweb.springboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import com.javaweb.springboot.dtos.UserDto;
import com.javaweb.springboot.entities.User;
import com.javaweb.springboot.services.UserService;

@Data
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	private final UserService service;
	private final ModelMapper modelMapper;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> getUserByID(@PathVariable(name = "id") int id) {
		User user = service.getUserByID(id);
		if (user == null) {
			return null;
		}
		return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable(name = "email") String email) {
		User user = service.getUserByEmail(email);
		if (user == null) {
			return null;
		}
		return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
	}

	@GetMapping(value = "/uid/{uid}")
	public ResponseEntity<UserDto> getUserByUID(@PathVariable(name = "uid") String uid) {
		User user = service.getUserByUID(uid);
		if (user == null || user.getPassword() != null) {
			return null;
		}
		return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<UserDto> getUserByUsernameAndPassword(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		User user = service.getUserByUsernameAndPassword(username, password);
		if (user == null) {
			return null;
		}
		return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDto> create(@RequestParam(value = "loginType") int loginTypeId,
			@RequestBody UserDto userDtoRequest) {
		User user = modelMapper.map(userDtoRequest, User.class);
		return new ResponseEntity<>(modelMapper.map(service.create(user, loginTypeId), UserDto.class), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDto> udpate(@PathVariable(name = "id") int id, @RequestBody UserDto userDtoRequest) {
		User user = modelMapper.map(userDtoRequest, User.class);
		return new ResponseEntity<>(modelMapper.map(service.update(id, user), UserDto.class), HttpStatus.OK);
	}
}
