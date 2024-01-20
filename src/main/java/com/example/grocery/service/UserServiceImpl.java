package com.example.grocery.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.repository.GroceryItemRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    // Get all grocery items available for users
    @Override
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    // Book a specific grocery item by ID
    @Override
    public void bookGroceryItem(Long itemId) {
        GroceryItem groceryItem = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Grocery item not found"));

        // Check if there is stock available for the selected grocery item
        if (groceryItem.getQuantity() > 0) {
            groceryItem.setQuantity(groceryItem.getQuantity() - 1);
            groceryItemRepository.save(groceryItem);
        } else {
            throw new RuntimeException("No stock available for the selected grocery item");
        }
    }

    // Create an order for multiple grocery items
    @Override
    public void createOrder(List<GroceryItem> groceryItems) {
        for (GroceryItem groceryItem : groceryItems) {
            Long requestedItemId = groceryItem.getId();
            int requestedQuantity = groceryItem.getQuantity();
            Optional<GroceryItem> requestedOrder = groceryItemRepository.findById(requestedItemId);

            // Check if the requested grocery item exists
            if (requestedOrder.isPresent()) {
                GroceryItem newOrder = requestedOrder.get();

                // Check if there is enough stock for the requested quantity
                if (newOrder.getQuantity() >= requestedQuantity) {
                    newOrder.setQuantity(newOrder.getQuantity() - requestedQuantity);
                    groceryItemRepository.save(newOrder);
                } else {
                    throw new IllegalArgumentException("Not enough stock for item with ID: " + requestedItemId);
                }
            } else {
                throw new NoSuchElementException("Grocery item not found with ID: " + requestedItemId);
            }
        }
    }
}
