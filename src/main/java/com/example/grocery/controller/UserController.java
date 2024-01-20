package com.example.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all grocery items available for users
    @GetMapping("/all-grocery-items")
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = userService.getAllGroceryItems();
        return new ResponseEntity<>(groceryItems, HttpStatus.OK);
    }

    // Book a specific grocery item by ID
    @PostMapping("/book-grocery-item/{itemId}")
    public ResponseEntity<Void> bookGroceryItem(@PathVariable Long itemId) {
        try {
            userService.bookGroceryItem(itemId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the case when there's an issue booking the grocery item
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create an order for multiple grocery items
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody List<GroceryItem> orderItems) {
        try {
            userService.createOrder(orderItems);
            return new ResponseEntity<>("Order created successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle the case when there's an issue creating the order
            return new ResponseEntity<>("Failed to create order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
