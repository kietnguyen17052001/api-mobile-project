package com.javaweb.springboot.controllers;

import java.lang.reflect.Type;
import java.util.List;

import com.javaweb.springboot.dtos.NewListDto;
import com.javaweb.springboot.entities.NewList;
import com.javaweb.springboot.services.NewListService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class NewListController {
	@Autowired
	private NewListService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{userId}/newLists")
	public List<NewListDto> getNewLists(@PathVariable(name = "userId") int userId) {
		List<NewList> newLists = service.getNewListsByUserId(userId);
		Type listType = new TypeToken<List<NewListDto>>() {
		}.getType();
		return modelMapper.map(newLists, listType);
	}

	@PostMapping(value = "/{userId}/newLists")
	public NewListDto create(@PathVariable(name = "userId") int userId, @RequestBody NewListDto newListDto) {
		NewList newList = modelMapper.map(newListDto, NewList.class);
		return modelMapper.map(service.create(newList, userId), NewListDto.class);
	}

	@PutMapping(value = "/newLists")
	public NewListDto update(@RequestBody NewListDto newListDto) {
		NewList newList = modelMapper.map(newListDto, NewList.class);
		return modelMapper.map(service.update(newListDto.getId(), newList), NewListDto.class);
	}

	@DeleteMapping(value = "/{userId}/newLists/{newListId}")
	public void delete(@PathVariable(name = "newListId") int newListId) {
		service.delete(newListId);
	}
}
