package com.javaweb.springboot.dtos;

import java.sql.Timestamp;
import java.util.List;

public class UserDto {
	private int id;
	private String email;
	private String displayName;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private List<NewListDto> newLists;
	private List<TaskDto> tasks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<NewListDto> getNewLists() {
		return newLists;
	}

	public void setNewLists(List<NewListDto> newLists) {
		this.newLists = newLists;
	}

	public List<TaskDto> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}

}
