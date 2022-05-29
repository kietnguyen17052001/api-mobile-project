package com.javaweb.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.javaweb.springboot.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findOneById(int id);

	User findByEmail(String email);

	@Query(value = "SELECT * FROM tbl_user u where u.username = :username and u.password = :password", nativeQuery = true)
	User findByUsernameAndPassword(@Param(value = "username") String username,
			@Param(value = "password") String password);
}
