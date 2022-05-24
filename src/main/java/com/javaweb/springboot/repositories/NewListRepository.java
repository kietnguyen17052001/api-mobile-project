package com.javaweb.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaweb.springboot.entities.NewList;

@Repository
public interface NewListRepository extends JpaRepository<NewList, Integer> {
	NewList findOneById(int id);

	@Query(value = "SELECT * FROM tbl_new_list n WHERE n.fk_user_id = :userId", nativeQuery = true)
	List<NewList> getNewListByUserId(@Param(value = "userId") int userId);
}
