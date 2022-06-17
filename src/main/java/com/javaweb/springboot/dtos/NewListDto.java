package com.javaweb.springboot.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class NewListDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int createdBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
