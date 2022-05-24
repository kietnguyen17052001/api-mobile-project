package com.javaweb.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.springboot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findOneById(int id);
}
