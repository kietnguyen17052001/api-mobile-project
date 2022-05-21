package com.javaweb.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javaweb.springboot.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findOneById(int id);

	User findByEmail(String email);
}
