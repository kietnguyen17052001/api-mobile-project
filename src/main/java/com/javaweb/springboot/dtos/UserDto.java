package com.javaweb.springboot.dtos;

import java.sql.Timestamp;

public class UserDto {
	private int id;
	private String displayName;
	private String email;
	private String username;
	private String password;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private int loginTypeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getLoginTypeId() {
		return loginTypeId;
	}

	public void setLoginTypeId(int loginTypeId) {
		this.loginTypeId = loginTypeId;
	}

}
