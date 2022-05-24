package com.javaweb.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.springboot.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findOneById(int id);
}
