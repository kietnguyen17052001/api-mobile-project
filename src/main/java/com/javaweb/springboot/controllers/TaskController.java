package com.javaweb.springboot.controllers;

import java.lang.reflect.Type;
import java.util.List;

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

import com.javaweb.springboot.dtos.TaskDto;
import com.javaweb.springboot.entities.Task;
import com.javaweb.springboot.services.TaskService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class TaskController {
	@Autowired
	private TaskService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{userId}/important/tasks")
	public List<TaskDto> getImportantTasks(@PathVariable(name = "userId") int userId) {
		List<Task> tasks = service.getImportantTasks(userId);
		Type listType = new TypeToken<List<TaskDto>>() {
		}.getType();
		return modelMapper.map(tasks, listType);
	}

	@GetMapping(value = "/{userId}/myday/tasks")
	public List<TaskDto> getMydayTasks(@PathVariable(name = "userId") int userId) {
		List<Task> tasks = service.getMydayTasks(userId);
		Type listType = new TypeToken<List<TaskDto>>() {
		}.getType();
		return modelMapper.map(tasks, listType);
	}

	@GetMapping(value = "/{userId}/newLists/{newListId}/tasks")
	public List<TaskDto> getNewListTasks(@PathVariable(name = "newListId") int newListId) {
		List<Task> tasks = service.getNewListTasks(newListId);
		Type listType = new TypeToken<List<TaskDto>>() {
		}.getType();
		return modelMapper.map(tasks, listType);
	}

	@PostMapping(value = "/{userId}/myday/tasks")
	public TaskDto createMyDayTask(@PathVariable(name = "userId") int userId, @RequestBody TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		return modelMapper.map(service.createMyDayTask(task, userId), TaskDto.class);
	}

	@PostMapping(value = "/{userId}/important/tasks")
	public TaskDto createImportantTask(@PathVariable(name = "userId") int userId, @RequestBody TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		return modelMapper.map(service.createImportantTask(task, userId), TaskDto.class);
	}

	@PostMapping(value = "/{userId}/newLists/{newListId}/tasks")
	public TaskDto createNewListTask(@PathVariable(name = "newListId") int newListId, @RequestBody TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		return modelMapper.map(service.createNewListTask(task, newListId), TaskDto.class);
	}

	@PutMapping(value = "/{userId}/tasks/{taskId}")
	public TaskDto updateTask(@PathVariable(name = "taskId") int taskId, @RequestBody TaskDto taskDtoRequest) {
		Task task = modelMapper.map(taskDtoRequest, Task.class);
		return modelMapper.map(service.update(taskId, task), TaskDto.class);
	}

	@PutMapping(value = "/{userId}/tasks/{taskId}/completed")
	public TaskDto complete(@PathVariable(name = "taskId") int taskId) {
		return modelMapper.map(service.complete(taskId), TaskDto.class);
	}

	@DeleteMapping(value = "/{userId}/tasks/{taskId}")
	public void deleteTask(@PathVariable(name = "taskId") int taskId) {
		service.delete(taskId);
	}
}
