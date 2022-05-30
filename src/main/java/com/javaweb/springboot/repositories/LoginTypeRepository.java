package com.javaweb.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.springboot.entities.LoginType;

@Repository
public interface LoginTypeRepository extends JpaRepository<LoginType, Integer> {
	LoginType findOneById(int id);
}
