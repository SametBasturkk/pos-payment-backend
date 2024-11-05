## Restaurant Menu Management System

This document outlines the features, technologies, architecture, and usage of a web-based restaurant menu management system built with Spring Boot.

### Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

### Features

- **Menu Management:**
    - Create, read, update, and delete categories, products, and menu items.
- **Order Processing:**
    - Place, view, and manage customer orders.
- **Authentication and Authorization:**
    - JWT-based token security.
- **Real-Time Updates:**
    - WebSocket support for live order updates.
- **Caching:**
    - Uses Redis for efficient data retrieval.
- **Logging:**
    - Integrated with Logstash for error and event logging.

### Technologies Used

- **Backend:** Java, Spring Boot
- **Security:** JWT for token-based authentication
- **Caching:** Redis
- **Database:** PostgreSQL
- **Logging and Monitoring:** ELK Stack (Elasticsearch, Logstash, and Kibana)
- **Real-Time Communication:** WebSocket

### Architecture

The system follows a standard Spring Boot REST API architecture with the following components:

- **Controllers:** Expose REST API endpoints.
- **Services:** Contain business logic.
- **Repositories:** Handle data access and persistence.
- **Utilities:** Provide auxiliary functions like token validation and data conversion.

### Project Structure

```
src/
├── main/
│   ├── java/com/pospayment/pospayment/
│   │   ├── configuration/       # Configurations for security, Redis, etc.
│   │   ├── controller/          # REST controllers (Category, Company, Menu, Order, etc.)
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── exception/           # Custom exceptions
│   │   ├── model/               # Entity models
│   │   ├── service/             # Service layer
│   │   ├── util/                # Utility classes (JWT, data converters)
│   │   └── PospaymentApplication.java  # Main application entry point
└── resources/
    ├── application.properties   # Application configuration
```

### Setup and Installation

1. **Clone the Repository:**

   ```bash
   git clone <repository-url>
   cd restaurant-menu-management
   ```

2. **Configure Database:**

    - **PostgreSQL:** Update `application.properties` with your PostgreSQL credentials:

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```

    - **MongoDB:** Update `application.properties` with your MongoDB connection details:

      ```properties
      spring.data.mongodb.uri=mongodb://localhost:27017/your_database
      ```

3. **Install Databases:**
    - **PostgreSQL:**
        - If not already installed, install PostgreSQL using the package manager for your OS. For Debian/Ubuntu:
          ```bash
          sudo apt update
          sudo apt install postgresql postgresql-contrib
          ```
    - **MongoDB:**
        - Download and install MongoDB from [https://www.mongodb.com/try/download/community](https://www.mongodb.com/try/download/community) following the instructions for your system.
        - Start the MongoDB server after installation.

4. **Install Redis:** If Redis is not installed, install it using the following commands for Debian/Ubuntu based systems:

   ```bash
   sudo apt update
   sudo apt install redis-server
   ```

   For other systems, refer to the Redis installation instructions for your operating system: [https://redis.io/docs/install/](https://redis.io/docs/install/)

5. **Start Redis Server:** After installation, start the Redis server:

   ```bash
   sudo systemctl start redis-server
   ```

6. **Run the Application:**

   ```bash
   ./mvnw spring-boot:run
   ```

7. **Access the Application:** The application should now be running at `http://localhost:3030`. You can use a tool like Postman or curl to test the API endpoints.

**Additional Notes:**

- You might need to create the database in PostgreSQL and MongoDB manually before running the application.
- Make sure your firewalls allow access to the ports used by each service (PostgreSQL: 5432, MongoDB: 27017, Redis: 6379).
- Consider configuring different ports if you have existing services running on these default ports.


### API Endpoints

Here's an overview of available API endpoints:

**Category Management**

- **Create Category**

   ```
   POST /category/create
   Headers: Authorization: Bearer <token>
   Body: { "name": "Beverages" }
   Description: Adds a new category.
   ```

- **List Categories**

   ```
   GET /category/list
   Description: Returns all categories.
   ```

- **Delete Category**

   ```
   POST /category/delete
   Headers: Authorization: Bearer <token>
   Query Parameter: id (Category ID)
   Description: Deletes a category by ID.
   ```

**Company Management**

- **Create Company**

   ```
   POST /company/create
   Headers: Authorization: Bearer <token>
   Body: { "name": "Restaurant A", "location": "City Center" }
   Description: Adds a new company.
   ```

- **List Companies**

   ```
   GET /company/list
   Description: Returns all companies.
   ```

**Menu Management**

- **Create Menu Item**

   ```
   POST /menu/create
   Headers: Authorization: Bearer <token>
   Body: { "name": "Pizza", "category": "Main Dish", "price": 12.99 }
   Description: Adds a new menu item.
   ```

- **List Menu Items**

   ```
   GET /menu/list
   Description: Retrieves all menu items.
   ```

- **Create Menu**

   ```
   POST /menu/create
   Headers: Authorization: Bearer <token>
   Body: { "name": "Lunch Menu", "categories": [ /* Array of Category IDs */ ], "company": /* Company ID */ }
   Description: Creates a new menu.
   ```

- **Get Menu by ID**

   ```
   GET /menu/{id}
   Description: Retrieves a menu by ID.
   ```

- **Get All Menus**

   ```
   GET /menu/get-all
   Headers: Authorization: Bearer <token>
   Description: Retrieves all menus.
   ```

- **Delete Menu**

   ```
   POST /menu/delete
   Headers: Authorization: Bearer <token>
   Query Parameter: id (Menu ID)
   Description: Deletes a menu by ID.
   ```

- **Deactivate Menu**

   ```
   POST /menu/deactive
   Headers: Authorization: Bearer <token>
   Query Parameter: id (Menu ID)
   Description: Deactivates a menu by ID.
   ```

**Order Management**

- **Create Order**

   ```
   POST /order/create
   Headers: Authorization: Bearer <token>
   Body: { "customerId": 1, "items": [{ "productId": 2, "quantity": 1 }] }
   Description: Places a new order.
   ```

- **Get Orders**

   ```
   GET /order/list
   Description: Retrieves all orders.
   ```

- **Change Order Status**

   ```
   POST /order/changeStatus
   Headers: Authorization: Bearer <token>
   Query Parameter: id (Order ID)
   Query Parameter: status (New Status)
   Description: Changes the status of an order.
   ```

**Product Management**

- **Create Product**

   ```
   POST /product/create
   Headers: Authorization: Bearer <token>
   Body: { "name": "Pizza", "category": /* Category ID */, "price": 12.99, "company": /* Company ID */ }
   Description: Creates a new product.
   ```

- **Get All Products**

   ```
   GET /product/get-all
   Headers: Authorization: Bearer <token>
   Description: Retrieves all products.
   ```

- **Get Product by ID**

   ```
   POST /product/get
   Headers: Authorization: Bearer <token>
   Query Parameter: id (Product ID)
   Description: Retrieves a product by ID.
   ```

- **Get Products by Category**

   ```
   POST /product/get-by-category
   Headers: Authorization: Bearer <token>
   Body: { "id": /* Category ID */ }
   Description: Retrieves products by category.
   ```

- **Update Product**

   ```
   POST /product/update
   Headers: Authorization: Bearer <token>
   Body: { "id": /* Product ID */, "name": "Pizza", "category": /* Category ID */, "price": 12.99, "company": /* Company ID */ }
   Description: Updates an existing product.
   ```

- **Delete Product**

   ```
   POST /product/delete
   Headers: Authorization: Bearer <token>
   Query Parameter: id (Product ID)
   Description: Deletes a product by ID.
   ```

- **Upload Image**

   ```
   POST /product/image-upload
   Headers: Authorization: Bearer <token>
   Body: { "image": /* Image Data */ }
   Description: Uploads an image for a product.
   ```

- **Get Image**

   ```
   GET /product/image/{filename}
   Description: Retrieves a product image by filename.
   ```

**Panel (User) Management**

- **Register User**

   ```
   POST /panel/register
   Body: { "name": "John", "surname": "Doe", "tckn": "12345678901", "username": "johndoe", "password": "password", "email": "john.doe@email.com", "phone": "+1234567890" }
   Description: Registers a new user.
   ```

- **Login User**

   ```
   POST /panel/login
   Body: { "username": "johndoe", "password": "password" }
   Description: Logs in an existing user.
   ```

- **Forgot Password Mail**

   ```
   POST /panel/forgot-password-mail
   Query Parameter: email (User email)
   Description: Sends a password reset email.
   ```

- **Forgot Password**

   ```
   POST /panel/forgot-password
   Query Parameter: token (Password reset token)
   Query Parameter: password (New password)
   Description: Resets the user's password using a token.
   ```

- **Reset Password**

   ```
   POST /panel/reset-password
   Headers: Authorization: Bearer <token>
   Query Parameter: oldPass (Current password)
   Query Parameter: newPass (New password)
   Description: Changes the user's password.
   ```

- **Get User Details**

   ```
   GET /panel/user-details
   Headers: Authorization: Bearer <token>
   Description: Retrieves the logged-in user's details.
   ```

- **Get Overview**

   ```
   GET /panel/overview
   Headers: Authorization: Bearer <token>
   Description: Gets an overview of the system (e.g., number of users, orders, products, etc.).
   ```

### Usage

- **Authentication:** To access most of the API endpoints requiring authorization, you need to obtain an authentication token by logging in using the `POST /panel/login` endpoint. The token should then be included in the `Authorization` header of subsequent requests using the format `Bearer <token>`.

- **Category Management:**
    - Create categories using `POST /category/create`.
    - Retrieve all categories using `GET /category/list`.
    - Delete a category by ID using `POST /category/delete`.

- **Company Management:**
    - Create companies using `POST /company/create`.
    - Retrieve all companies using `GET /company/list`.

- **Menu Management:**
    - Create menu items using `POST /menu/create`.
    - Retrieve all menu items using `GET /menu/list`.
    - Create menus using `POST /menu/create`.
    - Retrieve menus by ID using `GET /menu/{id}`.
    - Retrieve all menus using `GET /menu/get-all`.
    - Delete menus by ID using `POST /menu/delete`.
    - Deactivate menus by ID using `POST /menu/deactive`.

- **Order Management:**
    - Place orders using `POST /order/create`.
    - Retrieve all orders using `GET /order/list`.
    - Change the status of an order using `POST /order/changeStatus`.

- **Product Management:**
    - Create products using `POST /product/create`.
    - Retrieve all products using `GET /product/get-all`.
    - Retrieve products by ID using `POST /product/get`.
    - Retrieve products by category using `POST /product/get-by-category`.
    - Update products using `POST /product/update`.
    - Delete products by ID using `POST /product/delete`.
    - Upload images for products using `POST /product/image-upload`.
    - Retrieve product images by filename using `GET /product/image/{filename}`.

- **Panel (User) Management:**
    - Register new users using `POST /panel/register`.
    - Log in existing users using `POST /panel/login`.
    - Send password reset emails using `POST /panel/forgot-password-mail`.
    - Reset passwords using a token using `POST /panel/forgot-password`.
    - Change passwords using `POST /panel/reset-password`.
    - Retrieve user details using `GET /panel/user-details`.
    - Get an overview of the system using `GET /panel/overview`. 


