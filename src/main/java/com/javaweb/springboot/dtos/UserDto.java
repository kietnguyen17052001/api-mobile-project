package com.javaweb.springboot.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String displayName;
	private String email;
	private String username;
	private String password;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private int loginTypeId;
}
