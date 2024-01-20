package com.example.grocery.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.repository.GroceryItemRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    // Get all grocery items
    @Override
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    // Add a new grocery item
    @Override
    public void addGroceryItem(GroceryItem groceryItem) {
        groceryItemRepository.save(groceryItem);
    }

    // Remove a grocery item by ID
    @Override
    public void removeGroceryItem(Long itemId) {
        groceryItemRepository.deleteById(itemId);
    }

    // Update details of a grocery item by ID
    @Override
    public void updateGroceryItem(Long itemId, GroceryItem updatedItem) {
        GroceryItem existingItem = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Grocery item not found"));

        // Update details of the existing grocery item
        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setQuantity(updatedItem.getQuantity());

        // Save the updated grocery item
        groceryItemRepository.save(existingItem);
    }

    // Update inventory levels of a grocery item by ID
    @Override
    public void updateInventory(Long itemId, int quantityChange) {
        Optional<GroceryItem> optionalItem = groceryItemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            GroceryItem item = optionalItem.get();
            int currentQuantity = item.getQuantity();
            int newQuantity = currentQuantity + quantityChange;

            if (newQuantity >= 0) {
                // Ensure the new quantity is not negative
                item.setQuantity(newQuantity);
                groceryItemRepository.save(item);
            } else {
                // Throw an exception if the new quantity would be negative
                throw new IllegalArgumentException("Quantity cannot be negative");
            }
        } else {
            // Throw an exception if the grocery item with the specified ID is not found
            throw new NoSuchElementException("Grocery item not found with ID: " + itemId);
        }
    }

    // Other service methods...
}
