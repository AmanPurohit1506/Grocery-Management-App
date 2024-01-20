package com.example.grocery.service;

import java.util.List;

import com.example.grocery.entity.GroceryItem;


public interface UserService {
	List<GroceryItem> getAllGroceryItems();

	void bookGroceryItem(Long itemId);

	void createOrder(List<GroceryItem> orderItems);

}
