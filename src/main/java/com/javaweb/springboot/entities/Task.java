package com.javaweb.springboot.entities;

import java.io.Serializable;
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

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_task")
public class Task implements Serializable {
	private static final long serialVersionUID = 2247995789049326728L;
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
	@ManyToOne(targetEntity = NewList.class, cascade = CascadeType.PERSIST)
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

}
