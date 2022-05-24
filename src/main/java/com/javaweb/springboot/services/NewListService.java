package com.javaweb.springboot.services;

import java.util.List;

import com.javaweb.springboot.entities.NewList;

public interface NewListService {
	List<NewList> getNewListsByUserId(int userId);

	NewList create(NewList newList, int userId);

	NewList update(int newListId, NewList newList);

	void delete(int newListId);
}
