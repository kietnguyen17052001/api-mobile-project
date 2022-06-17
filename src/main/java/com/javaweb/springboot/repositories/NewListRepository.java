package com.javaweb.springboot.repositories;

import com.javaweb.springboot.entities.NewList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewListRepository extends JpaRepository<NewList, Integer> {
	NewList findOneById(int id);

//	@Query(value = "SELECT * FROM tbl_new_list n WHERE n.fk_user_id = :userId", nativeQuery = true)
//	List<NewList> getNewListByUserId(@Param(value = "userId") int userId);
}
