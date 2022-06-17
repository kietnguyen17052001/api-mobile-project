package com.javaweb.springboot.services.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.javaweb.springboot.entities.Task;
import com.javaweb.springboot.repositories.CategoryRepository;
import com.javaweb.springboot.repositories.NewListRepository;
import com.javaweb.springboot.repositories.TaskRepository;
import com.javaweb.springboot.repositories.UserRepository;
import com.javaweb.springboot.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
	private static final int MYDAY_ID = 1;
	private static final int IMPORTANT_ID = 2;
	private static final int NEWLIST_ID = 3;

	@Autowired
	private TaskRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private NewListRepository newListRepository;
	@Autowired
	private UserRepository userRepository;

	public List<Task> getTasks(int userId, int categoryId) {
		return repository.findAll().stream()
				.filter(t -> t.getUser().getId() == userId && t.getCategory().getId() == categoryId)
				.collect(Collectors.toList());
	}

	public Task setStatusAndTime(Task task) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		task.setCompleted(false);
		task.setCreatedAt(timestamp);
		task.setUpdatedAt(timestamp);
		return task;
	}

	@Override
	public List<Task> getImportantTasks(int userId) {
//		return repository.getTaskByIdCategory(userId, IMPORTANT_ID);
		return getTasks(userId, IMPORTANT_ID);
	}

	@Override
	public List<Task> getMydayTasks(int userId) {
//		return repository.getTaskByIdCategory(userId, MYDAY_ID);
		return getTasks(userId, MYDAY_ID);
	}

	@Override
	public List<Task> getNewListTasks(int newListId) {
		return newListRepository.findOneById(newListId).getTasks();
	}

	@Override
	public Task createMyDayTask(Task task, int userId) {
		task = setStatusAndTime(task);
		task.setUser(userRepository.findOneById(userId));
		task.setCategory(categoryRepository.findOneById(MYDAY_ID));
		repository.save(task);
		return task;
	}

	@Override
	public Task createImportantTask(Task task, int userId) {
		task = setStatusAndTime(task);
		task.setUser(userRepository.findOneById(userId));
		task.setCategory(categoryRepository.findOneById(IMPORTANT_ID));
		repository.save(task);
		return task;
	}

	@Override
	public Task createNewListTask(Task task, int newListId) {
		task = setStatusAndTime(task);
		task.setUser(newListRepository.findOneById(newListId).getUser());
		task.setNewList(newListRepository.findOneById(newListId));
		task.setCategory(categoryRepository.findOneById(NEWLIST_ID));
		repository.save(task);
		return task;
	}

	@Override
	public Task update(int taskId, Task taskRequest) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Task task = repository.findOneById(taskId);
		task.setUpdatedAt(timestamp);
		if (taskRequest.getName() != null) {
			task.setName(taskRequest.getName());
		}
		if (taskRequest.getDescription() != null) {
			task.setDescription(taskRequest.getDescription());
		}
		repository.save(task);
		return task;
	}

	@Override
	public Task complete(int taskId) {
		Task task = repository.findOneById(taskId);
		task.setCompleted(!task.isCompleted());
		repository.save(task);
		return task;
	}

	@Override
	public void delete(int taskId) {
		repository.deleteById(taskId);
	}

}
