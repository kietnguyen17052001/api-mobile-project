package com.javaweb.springboot.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import com.javaweb.springboot.dtos.NewListDto;
import com.javaweb.springboot.entities.NewList;
import com.javaweb.springboot.services.NewListService;

@Data
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class NewListController {
	private final NewListService service;
	private final ModelMapper modelMapper;

	@GetMapping(value = "/{userId}/newLists")
	public ResponseEntity<List<NewListDto>> getNewLists(@PathVariable(name = "userId") int userId) {
		List<NewList> newLists = service.getNewListsByUserId(userId);
		Type listType = new TypeToken<List<NewListDto>>() {
		}.getType();
		return new ResponseEntity<>(modelMapper.map(newLists, listType), HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/newLists")
	public ResponseEntity<NewListDto> create(@PathVariable(name = "userId") int userId,
			@RequestBody NewListDto newListDto) {
		NewList newList = modelMapper.map(newListDto, NewList.class);
		return new ResponseEntity<>(modelMapper.map(service.create(newList, userId), NewListDto.class), HttpStatus.OK);
	}

	@PutMapping(value = "/{userId}/newLists/{newListId}")
	public ResponseEntity<NewListDto> update(@PathVariable(name = "newListId") int newListId,
			@RequestBody NewListDto newListDtoRequest) {
		NewList newList = modelMapper.map(newListDtoRequest, NewList.class);
		return new ResponseEntity<>(modelMapper.map(service.update(newListId, newList), NewListDto.class),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}/newLists/{newListId}")
	public ResponseEntity<?> delete(@PathVariable(name = "newListId") int newListId) {
		service.delete(newListId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
