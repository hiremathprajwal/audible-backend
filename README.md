🎧 **Audible Backend**

Spring Boot REST API for an audiobook platform similar to Audible.
Provides authentication, cart checkout, payment handling, and personal library management.

🧱 **Tech Stack**

Java 17
Spring Boot
Spring Data JPA (Hibernate)
MySQL
Maven
Log4j2
JUnit, Mockito, MockMvc

✨ **Core Features**

Secure user authentication with BCrypt password hashing
Password history validation (prevents reuse of last 3 passwords)
Cart → Order checkout workflow
Payment card discount system (5% debit, 10% credit)
Automatic audiobook library update after purchase
Global exception handling & validation
Centralized logging with Log4j2
CORS enabled for frontend integration

⚙️ **Setup & Run**

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

### Customer APIs
- `POST /customer/register` - Register new customer
- `POST /customer/login` - Login
- `GET /customer/view-profile/{customerId}` - View profile
- `PUT /customer/change-password` - Change password
- `PUT /customer/forgot-password` - Forgot password

### 📚 AudiobookAPIs
| Method | Endpoint                               | Description                 |
| ------ | -------------------------------------- | --------------------------- |
| POST   | `/api/audiobooks`                      | Add a new audiobook         |
| GET    | `/api/audiobooks/{audioId}`            | Get audiobook details by ID |
| GET    | `/api/audiobooks`                      | Get all audiobooks          |
| GET    | `/api/audiobooks/search?title={title}` | Search audiobooks by title  |
| GET    | `/api/audiobooks/author/{authorId}`    | Get audiobooks by author    |


### Cart APIs
- `GET /cart/viewcart/{customerId}` - View cart
- `POST /cart/addtocart/{customerId}/{audioId}` - Add to cart
- `DELETE /cart/remove/{customerId}/{audioId}` - Remove from cart

### Payment APIs
- `POST /payment/add-card` - Add payment card
- `GET /payment/saved-cards/{customerId}` - Get saved cards
- `POST /payment/process` - Process payment

## Features

- User authentication with password hashing (BCrypt)
- Password history validation (last 3 passwords)
- Cart management with validation
- Payment processing with discount calculation (5% debit, 10% credit)
- Library management for purchased audiobooks
- CORS enabled for frontend integration

