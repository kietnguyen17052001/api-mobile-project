package com.javaweb.springboot.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(name = "tbl_user", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "username" }))
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(length = 50, name = "email", nullable = true)
	private String email;
	@Column(length = 50, name = "username", nullable = true)
	private String username;
	@Column(length = 255, name = "password", nullable = true)
	private String password;
	@Column(length = 255, name = "display_name", nullable = false)
	private String displayName;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@ManyToOne(targetEntity = LoginType.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_login_type_id", nullable = false)
	private LoginType loginType;
	@OneToMany(mappedBy = "user")
	private List<NewList> newLists;
	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

}
