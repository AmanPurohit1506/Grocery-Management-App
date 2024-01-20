# Grocery Booking API

## Overview

The Grocery Booking API is designed to facilitate the management of grocery items with roles for both Admins and Users. Admins can perform tasks like adding, viewing, updating, and removing grocery items, as well as managing inventory levels. Users, on the other hand, can view available grocery items and book multiple items in a single order.

## Features

### Admin Responsibilities

1. **Add New Grocery Items**
   - **Endpoint:** `/admin/add-grocery-item`
   - **Method:** POST
   - **Request Body:** JSON representing the new grocery item

2. **View Existing Grocery Items**
   - **Endpoint:** `/admin/all-grocery-items`
   - **Method:** GET
   - **Response:** List of existing grocery items

3. **Remove Grocery Items**
   - **Endpoint:** `/admin/remove-grocery-item/{itemId}`
   - **Method:** DELETE
   - **Path Variable:** ID of the grocery item to be removed

4. **Update Details of Existing Grocery Items**
   - **Endpoint:** `/admin/update-grocery-item/{itemId}`
   - **Method:** PUT
   - **Path Variable:** ID of the grocery item to be updated
   - **Request Body:** JSON representing the updated details

5. **Manage Inventory Levels**
   - **Endpoint:** `/admin/update-inventory/{itemId}`
   - **Method:** PATCH
   - **Path Variable:** ID of the grocery item
   - **Request Parameter:** `quantityChange` indicating the change in inventory level

### User Responsibilities

1. **View Available Grocery Items**
   - **Endpoint:** `/user/all-grocery-items`
   - **Method:** GET
   - **Response:** List of available grocery items

2. **Book Multiple Grocery Items in a Single Order**
   - **Endpoint:** `/user/create-order`
   - **Method:** POST
   - **Request Body:** JSON representing a list of grocery items to be booked

## Advanced Challenge

- **Containerization with Docker**
  - To containerize the application for ease of deployment and scaling, use Docker.
  - Build the Docker image with the provided Dockerfile.
  - Run the Docker container, ensuring it is properly connected to the chosen relational database.

## Database

- The application uses a relational database.
- Configuration details for the database connection can be found in the application properties file.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/grocery-booking-api.git
   cd grocery-booking-api
2. **Build and Run the Application**
   ```bash
   mvn clean package
   java -jar target/grocery-booking-api-0.0.1-SNAPSHOT.jar

## Access the API

- **Admin API:** [http://localhost:8080/admin](http://localhost:8080/admin)
- **User API:** [http://localhost:8080/user](http://localhost:8080/user)

## Access H2 Database Console

- [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - **JDBC URL:** jdbc:h2:mem:testdb
  - **User Name:** sa
  - **Password:** (leave it blank)
