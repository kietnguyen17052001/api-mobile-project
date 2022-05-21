package com.javaweb.springboot.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(length = 255, nullable = false)
	private String name;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;
	@ManyToOne(targetEntity = Category.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_category_id", nullable = false)
	private Category category;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_new_list_id", nullable = true)
	private NewList newList;
	@Column(length = 255, name = "description", nullable = false)
	private String description;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@Column
	private boolean completed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public NewList getNewList() {
		return newList;
	}

	public void setNewList(NewList newList) {
		this.newList = newList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

}
