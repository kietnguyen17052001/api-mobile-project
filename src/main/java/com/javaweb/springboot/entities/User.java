package com.javaweb.springboot.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tbl_user", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "username" }))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(length = 50, name = "email", nullable = false)
	private String email;
	@Column(length = 50, name = "username", nullable = true)
	private String username;
	@Column(name = "password", nullable = true)
	private String password;
	@Column(length = 255, name = "display_name", nullable = true)
	private String displayName;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@ManyToOne(targetEntity = LoginType.class, cascade = CascadeType.PERSIST)
	@Column(name = "fk_logintype_id", nullable = false)
	private LoginType loginType;
	@OneToMany(mappedBy = "user")
	private List<NewList> newLists;
	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

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

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public List<NewList> getNewLists() {
		return newLists;
	}

	public void setNewLists(List<NewList> newLists) {
		this.newLists = newLists;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

}
