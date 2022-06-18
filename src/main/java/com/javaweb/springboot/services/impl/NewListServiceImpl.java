package com.javaweb.springboot.services.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.javaweb.springboot.entities.NewList;
import com.javaweb.springboot.entities.Task;
import com.javaweb.springboot.repositories.NewListRepository;
import com.javaweb.springboot.repositories.TaskRepository;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.NewListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewListServiceImpl implements NewListService {

	@Autowired
	private NewListRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<NewList> getNewListsByUserId(int userId) {
		return repository.findAll().stream().filter(l -> l.getUser().getId() == userId).collect(Collectors.toList());
	}

	@Override
	public NewList create(NewList newList, int userId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		newList.setUser(userRepository.findOneById(userId));
		newList.setCreatedAt(timestamp);
		newList.setUpdatedAt(timestamp);
		repository.save(newList);
		return newList;
	}

	@Override
	public NewList update(NewList newList) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		newList.setUpdatedAt(timestamp);
		repository.save(newList);
		return newList;
	}

	@Override
	public void delete(int newListId) {
		NewList newList = repository.findOneById(newListId);
		for (Task task : newList.getTasks()) {
			taskRepository.delete(task);
		}
		repository.delete(newList);
	}

}
