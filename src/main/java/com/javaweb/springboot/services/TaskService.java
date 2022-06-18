package com.javaweb.springboot.services;

import java.util.List;

import com.javaweb.springboot.entities.Task;

public interface TaskService {
	List<Task> getImportantTasks(int userId);

	List<Task> getMydayTasks(int userId);

	List<Task> getNewListTasks(int newListId);

	Task createMyDayTask(Task task, int userId);

	Task createImportantTask(Task task, int userId);

	Task createNewListTask(Task task, int newListId);

	Task update(Task task);

	void delete(int taskId);
}
