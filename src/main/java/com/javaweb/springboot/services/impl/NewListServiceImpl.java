package com.javaweb.springboot.services.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.springboot.entities.NewList;
import com.javaweb.springboot.entities.Task;
import com.javaweb.springboot.repositories.NewListRepository;
import com.javaweb.springboot.repositories.TaskRepository;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.NewListService;

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
		return repository.getNewListByUserId(userId);
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
	public NewList update(int newListId, NewList newListRequest) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		NewList newList = repository.findOneById(newListId);
		newList.setName(newListRequest.getName());
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
