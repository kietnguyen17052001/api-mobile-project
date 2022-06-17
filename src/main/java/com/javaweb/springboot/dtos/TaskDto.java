package com.javaweb.springboot.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TaskDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int createdBy;
	private int categoryId;
	private int newListId;
	private String description;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private boolean completed;
}
