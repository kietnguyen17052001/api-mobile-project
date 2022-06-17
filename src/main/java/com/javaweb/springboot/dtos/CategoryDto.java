package com.javaweb.springboot.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
}
