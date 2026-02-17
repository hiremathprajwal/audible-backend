-- Audible Database Schema
-- Create database
CREATE DATABASE IF NOT EXISTS audible_db;
USE audible_db;

-- Table: customers
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: password_history
CREATE TABLE password_history (
    password_history_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- Table: authors
CREATE TABLE authors (
    author_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(100) NOT NULL,
    author_email VARCHAR(100),
    description TEXT
);

-- Table: audiobooks
CREATE TABLE audiobooks (
    audio_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    narrator VARCHAR(100),
    duration VARCHAR(50),
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    cover_image VARCHAR(500),
    cover_image_url VARCHAR(500),
    src VARCHAR(500),
    src_url VARCHAR(500),
    short_clip VARCHAR(500),
    short_clip_url VARCHAR(500),
    total_star DECIMAL(3, 2) DEFAULT 0.00,
    author_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE SET NULL
);

-- Table: carts
CREATE TABLE carts (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    UNIQUE KEY unique_customer_cart (customer_id)
);

-- Table: cart_items
CREATE TABLE cart_items (
    audio_cart_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    audio_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (audio_id) REFERENCES audiobooks(audio_id) ON DELETE CASCADE,
    UNIQUE KEY unique_cart_audio (cart_id, audio_id)
);

-- Table: library
CREATE TABLE library (
    library_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    audio_id INT NOT NULL,
    last_position INT DEFAULT 0,
    is_completed BOOLEAN DEFAULT FALSE,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (audio_id) REFERENCES audiobooks(audio_id) ON DELETE CASCADE,
    UNIQUE KEY unique_customer_audio (customer_id, audio_id)
);

-- Table: payment_cards
CREATE TABLE payment_cards (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    card_number VARCHAR(16) NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    expiry_date DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    card_type ENUM('Credit Card', 'Debit Card') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- Table: orders
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    discount_amount DECIMAL(10, 2) DEFAULT 0.00,
    final_amount DECIMAL(10, 2) NOT NULL,
    payment_status ENUM('PENDING', 'SUCCESS', 'FAILED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- Table: order_items
CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    audio_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (audio_id) REFERENCES audiobooks(audio_id) ON DELETE CASCADE
);

-- Create indexes for performance
CREATE INDEX idx_customer_email ON customers(email);
CREATE INDEX idx_customer_username ON customers(username);
CREATE INDEX idx_audiobook_title ON audiobooks(title);
CREATE INDEX idx_audiobook_author ON audiobooks(author_id);

