package com.javaweb.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaweb.springboot.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	Task findOneById(int taskId);

	@Query(value = "SELECT * FROM tbl_task t where t.fk_user_id = :userId and t.fk_category_id = :categoryID", nativeQuery = true)
	List<Task> getTaskByIdCategory(@Param(value = "userId") int userId, @Param(value = "categoryID") int categoryId);

}
