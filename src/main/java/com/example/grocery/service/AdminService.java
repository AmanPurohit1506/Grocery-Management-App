package com.example.grocery.service;

import java.util.List;

import com.example.grocery.entity.GroceryItem;

public interface AdminService {
	List<GroceryItem> getAllGroceryItems();

	void addGroceryItem(GroceryItem groceryItem);

	void removeGroceryItem(Long itemId);

	void updateGroceryItem(Long itemId, GroceryItem updatedItem);

	void updateInventory(Long itemId, int quantityChange);

}
