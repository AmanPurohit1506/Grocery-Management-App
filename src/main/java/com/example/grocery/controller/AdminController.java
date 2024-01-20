package com.example.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get all grocery items
    @GetMapping("/all-grocery-items")
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = adminService.getAllGroceryItems();
        return new ResponseEntity<>(groceryItems, HttpStatus.OK);
    }

    // Add a new grocery item
    @PostMapping("/add-grocery-item")
    public ResponseEntity<Void> addGroceryItem(@RequestBody GroceryItem groceryItem) {
        adminService.addGroceryItem(groceryItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update details of a grocery item
    @PutMapping("/update-grocery-item/{itemId}")
    public ResponseEntity<String> updateGroceryItem(@PathVariable Long itemId, @RequestBody GroceryItem updatedItem) {
        try {
            adminService.updateGroceryItem(itemId, updatedItem);
            return new ResponseEntity<>("Grocery Item Updated", HttpStatus.OK);
        } catch (Exception e) {
            // Handle case when the grocery item with the specified ID is not found
            return new ResponseEntity<>("Grocery Item Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // Remove a grocery item
    @DeleteMapping("/remove-grocery-item/{itemId}")
    public ResponseEntity<Void> removeGroceryItem(@PathVariable Long itemId) {
        adminService.removeGroceryItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Manage inventory levels of a grocery item
    @PatchMapping("/update-inventory/{itemId}")
    public ResponseEntity<String> updateInventory(@PathVariable Long itemId, @RequestParam int quantityChange) {
        try {
            adminService.updateInventory(itemId, quantityChange);
            return new ResponseEntity<>("Inventory updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle case when there is an issue updating the inventory
            return new ResponseEntity<>("Failed to update inventory: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
