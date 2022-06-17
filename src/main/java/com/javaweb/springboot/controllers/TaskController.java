package com.javaweb.springboot.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.javaweb.springboot.dtos.TaskDto;
import com.javaweb.springboot.entities.Task;
import com.javaweb.springboot.services.TaskService;

@Data
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class TaskController {
	private final TaskService service;
	private final ModelMapper modelMapper;

	@GetMapping(value = "/{userId}/important/tasks")
	public ResponseEntity<List<TaskDto>> getImportantTasks(@PathVariable(name = "userId") int userId) {
		List<Task> tasks = service.getImportantTasks(userId);
		Type listType = new TypeToken<List<TaskDto>>() {
		}.getType();
		return new ResponseEntity<>(modelMapper.map(tasks, listType), HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/myday/tasks")
	public ResponseEntity<List<TaskDto>> getMydayTasks(@PathVariable(name = "userId") int userId) {
		List<Task> tasks = service.getMydayTasks(userId);
		Type listType = new TypeToken<List<TaskDto>>() {
		}.getType();
		return new ResponseEntity<>(modelMapper.map(tasks, listType), HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/newLists/{newListId}/tasks")
	public ResponseEntity<List<TaskDto>> getNewListTasks(@PathVariable(name = "newListId") int newListId) {
		List<Task> tasks = service.getNewListTasks(newListId);
		Type listType = new TypeToken<List<TaskDto>>() {
		}.getType();
		return new ResponseEntity<>(modelMapper.map(tasks, listType), HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/myday/tasks")
	public ResponseEntity<TaskDto> createMyDayTask(@PathVariable(name = "userId") int userId,
			@RequestBody TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		return new ResponseEntity<>(modelMapper.map(service.createMyDayTask(task, userId), TaskDto.class),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/important/tasks")
	public ResponseEntity<TaskDto> createImportantTask(@PathVariable(name = "userId") int userId,
			@RequestBody TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		return new ResponseEntity<>(modelMapper.map(service.createImportantTask(task, userId), TaskDto.class),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/newLists/{newListId}/tasks")
	public ResponseEntity<TaskDto> createNewListTask(@PathVariable(name = "newListId") int newListId,
			@RequestBody TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		return new ResponseEntity<>(modelMapper.map(service.createNewListTask(task, newListId), TaskDto.class),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{userId}/tasks/{taskId}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable(name = "taskId") int taskId,
			@RequestBody TaskDto taskDtoRequest) {
		Task task = modelMapper.map(taskDtoRequest, Task.class);
		return new ResponseEntity<>(modelMapper.map(service.update(taskId, task), TaskDto.class), HttpStatus.OK);
	}

	@PutMapping(value = "/{userId}/tasks/{taskId}/completed")
	public ResponseEntity<TaskDto> complete(@PathVariable(name = "taskId") int taskId) {
		return new ResponseEntity<>(modelMapper.map(service.complete(taskId), TaskDto.class), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}/tasks/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable(name = "taskId") int taskId) {
		service.delete(taskId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
