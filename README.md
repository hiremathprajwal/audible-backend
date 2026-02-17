# Audible Backend

Spring Boot REST API for the Audible audiobook platform.

## Setup

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

### Audiobook APIs
- `GET /audiobook/all-audiobooks` - Get all audiobooks
- `GET /audiobook/view-audio/{audioId}` - Get audiobook by ID
- `GET /audiobook/searchtitle/{title}/{author}` - Search audiobooks
- `GET /audiobook/category/get/{pageNo}/{pageSize}/{sortBy}` - Get paginated audiobooks
- `GET /audiobook/catalogue/{authorId}` - Get audiobooks by author
- `GET /audiobook/authors` - Get all authors
- `GET /audiobook/view-library/{customerId}` - View library
- `POST /audiobook/add/{customerId}/{audioId}` - Add to library
- `DELETE /audiobook/delete/{customerId}/{audioId}` - Remove from library
- `POST /audiobook/addto-library/{customerId}` - Add all from cart to library

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

