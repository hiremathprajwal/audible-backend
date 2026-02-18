### 🎧 Audible Backend 

Spring Boot REST API for an audiobook platform similar to Audible.
Provides authentication, cart checkout, payment handling, and personal library management.

### 🧱 Tech Stack

Java 17
Spring Boot
Spring Data JPA (Hibernate)
MySQL
Maven
Log4j2
JUnit, Mockito, MockMvc

### ✨ Core Features

Secure user authentication with BCrypt password hashing
Password history validation (prevents reuse of last 3 passwords)
Cart → Order checkout workflow
Payment card discount system (5% debit, 10% credit)
Automatic audiobook library update after purchase
Global exception handling & validation
Centralized logging with Log4j2
CORS enabled for frontend integration

### ⚙️ Setup & Run

1. **Database Setup:**
   - Create MySQL database: `CREATE DATABASE audible_db;`
   - Run the schema script: `database/schema.sql`
   - Run the sample data script: `database/sample_data.sql`

2. **Configuration:**
   - Update `application.properties` with your MySQL credentials:
     ```properties
     spring.datasource.username=root
     spring.datasource.password=your_password
     ```

3. **Build and Run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The API will run on http://localhost:8080

## API Endpoints

### 👤 Customer APIs
| Method | Endpoint                             | Description           |
| ------ | ------------------------------------ | --------------------- |
| POST   | `/api/customers/register`            | Register new customer |
| POST   | `/api/customers/login`               | Login user            |
| GET    | `/api/customers/{customerId}`        | Get customer by ID    |
| GET    | `/api/customers/email/{email}`       | Get customer by email |
| GET    | `/api/customers`                     | Get all customers     |
| PUT    | `/api/customers/change-password`     | Change password       |
| PUT    | `/api/customers/forgot-password`     | Reset password        |
| GET    | `/api/customers/username/{username}` | Get email by username |

### ✍️ Author APIs

| Method | Endpoint                  | Description      |
| ------ | ------------------------- | ---------------- |
| POST   | `/api/authors`            | Add new author   |
| GET    | `/api/authors/{authorId}` | Get author by ID |
| GET    | `/api/authors`            | Get all authors  |


### 📚 Audiobook APIs
| Method | Endpoint                               | Description                 |
| ------ | -------------------------------------- | --------------------------- |
| POST   | `/api/audiobooks`                      | Add a new audiobook         |
| GET    | `/api/audiobooks/{audioId}`            | Get audiobook details by ID |
| GET    | `/api/audiobooks`                      | Get all audiobooks          |
| GET    | `/api/audiobooks/search?title={title}` | Search audiobooks by title  |
| GET    | `/api/audiobooks/author/{authorId}`    | Get audiobooks by author    |


### 🛒 Cart APIs
| Method | Endpoint                                   | Description                |
| ------ | ------------------------------------------ | -------------------------- |
| GET    | `/api/carts/{customerId}`                  | View customer cart         |
| POST   | `/api/carts/{customerId}/add/{audioId}`    | Add audiobook to cart      |
| DELETE | `/api/carts/{customerId}/remove/{audioId}` | Remove audiobook from cart |
| DELETE | `/api/carts/{customerId}/clear`            | Clear entire cart          |


### 💳 Payment Card APIs
| Method | Endpoint                                   | Description                        |
| ------ | ------------------------------------------ | ---------------------------------- |
| POST   | `/api/payment-cards`                       | Add a new payment card             |
| DELETE | `/api/payment-cards/{cardId}`              | Delete payment card                |
| GET    | `/api/payment-cards/customer/{customerId}` | Get all saved cards for a customer |

### ⭐ Wishlist APIs
| Method | Endpoint                               | Description                    |
| ------ | -------------------------------------- | ------------------------------ |
| POST   | `/api/wishlist/{customerId}/{audioId}` | Add audiobook to wishlist      |
| DELETE | `/api/wishlist/{customerId}/{audioId}` | Remove audiobook from wishlist |
| GET    | `/api/wishlist/{customerId}`           | View customer wishlist         |

### 📦 Order APIs
| Method | Endpoint                                                | Description                                                |
| ------ | ------------------------------------------------------- | ---------------------------------------------------------- |
| POST   | `/api/orders/{customerId}/place?paymentMethod={method}` | Place order from cart (Credit/Debit card discount applied) |
| GET    | `/api/orders/{customerId}`                              | Get order history for customer                             |

### 📚 Library APIs
| Method | Endpoint                              | Description                              |
| ------ | ------------------------------------- | ---------------------------------------- |
| GET    | `/api/library/{customerId}`           | View purchased audiobooks (user library) |
| DELETE | `/api/library/{customerId}/{audioId}` | Remove audiobook from library            |

### 🧩 Architecture

Layered architecture (Controller → Service → Repository)
DTO pattern implementation
Global exception advisor
AOP-based logging
Unit & API testing

## Features

- User authentication with password hashing (BCrypt)
- Password history validation (last 3 passwords)
- Cart management with validation
- Payment processing with discount calculation (5% debit, 10% credit)
- Library management for purchased audiobooks
- CORS enabled for frontend integration

