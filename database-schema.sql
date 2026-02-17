-- =========================================
-- DATABASE: audible_db (Clean Updated Schema)
-- =========================================
DROP DATABASE IF EXISTS audible_db;
CREATE DATABASE IF NOT EXISTS audible_db;
USE audible_db;

DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS payment_cards;
DROP TABLE IF EXISTS library;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS audiobooks;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS password_history;
DROP TABLE IF EXISTS customers;

-- ================================
-- 1. CUSTOMERS
-- ================================
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- ================================
-- 2. PASSWORD HISTORY (last 3 passwords)
-- ================================
CREATE TABLE password_history (
    password_history_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- ================================
-- 3. AUTHORS
-- ================================
CREATE TABLE authors (
    author_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(100) NOT NULL,
    author_email VARCHAR(100),
    description TEXT
);

-- ================================
-- 4. AUDIOBOOKS
-- ================================
CREATE TABLE audiobooks (
    audio_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    narrator VARCHAR(100),
    duration INT,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    cover_image VARCHAR(500),
    audio_file VARCHAR(500),
    short_clip VARCHAR(500),
    total_star FLOAT DEFAULT 0,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE SET NULL
);

-- ================================
-- 5. CART
-- ================================
CREATE TABLE carts (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL UNIQUE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- ================================
-- 6. CART ITEMS
-- ================================
CREATE TABLE cart_items (
    audio_cart_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    audio_id INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (audio_id) REFERENCES audiobooks(audio_id) ON DELETE CASCADE,
    UNIQUE KEY unique_cart_audio (cart_id, audio_id)
);

-- ================================
-- 7. LIBRARY
-- ================================
CREATE TABLE library (
    library_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    audio_id INT NOT NULL,
    last_position INT DEFAULT 0,
    is_completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (audio_id) REFERENCES audiobooks(audio_id) ON DELETE CASCADE,
    UNIQUE KEY unique_customer_audio (customer_id, audio_id)
);

-- ================================
-- 8. PAYMENT CARDS
-- ================================
CREATE TABLE payment_cards (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    card_number VARCHAR(16) NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    expiry_date DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    card_type ENUM('Credit Card', 'Debit Card') NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- ================================
-- 9. ORDERS
-- ================================
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    discount_amount DECIMAL(10,2) DEFAULT 0.00,
    final_amount DECIMAL(10,2) NOT NULL,
    payment_status ENUM('PENDING','SUCCESS','FAILED') DEFAULT 'PENDING',
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- ================================
-- 10. ORDER ITEMS
-- ================================
CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    audio_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (audio_id) REFERENCES audiobooks(audio_id) ON DELETE CASCADE
);

-- ================================
-- INDEXES
-- ================================
CREATE INDEX idx_customer_email ON customers(email);
CREATE INDEX idx_customer_username ON customers(username);
CREATE INDEX idx_audiobook_title ON audiobooks(title);
CREATE INDEX idx_audiobook_author ON audiobooks(author_id);

-- ================================
-- SAMPLE DATA
-- ================================

-- CUSTOMERS
INSERT INTO customers (username, name, email, password) VALUES
('yokesh01', 'Yokesh Kumar', 'yokesh@example.com', '$2a$10$abcdefghijklmnopqrstuv'),
('arun23', 'Arun Raj', 'arun@example.com', '$2a$10$abcdefghijklmnopqrstuv'),
('megha11', 'Megha Devi', 'megha@example.com', '$2a$10$abcdefghijklmnopqrstuv');

-- PASSWORD HISTORY
INSERT INTO password_history (customer_id, password_hash) VALUES
(1, '$2a$10$oldpasswordhashaaa'),
(1, '$2a$10$oldpasswordhashbbb'),
(1, '$2a$10$oldpasswordhashccc'),
(2, '$2a$10$oldpasswordhashddd'),
(3, '$2a$10$oldpasswordhasheee');

-- AUTHORS
INSERT INTO authors (author_name, author_email, description) VALUES
('J.K. Rowling', 'jkrowling@hogwarts.com', 'British author of Harry Potter'),
('Stephen King', 'stephenking@mail.com', 'American author of horror and suspense'),
('Mel Robbins', 'melrobbins@mail.com', 'Motivational speaker and author'),
('Morgan Housel', 'mhousel@mail.com', 'Author of The Psychology of Money');

-- AUDIOBOOKS
INSERT INTO audiobooks (title, narrator, duration, description, price, cover_image, audio_file, short_clip, total_star, author_id) VALUES
('Harry Potter and the Goblet of Fire', 'Jim Dale', 36000, 'Harry enters Triwizard Tournament', 1200.00, 
 '/images/audiobooks/harry-potter-goblet-fire.jpg', 
 '/audio/audiobooks/harry-potter-goblet-fire.mp3', 
 '/audio/clips/harry-potter-goblet-fire-clip.mp3', 
 4.9, 1),
('The Shining', 'Campbell Scott', 55800, 'Horror classic about isolated hotel', 899.00, 
 '/images/audiobooks/the-shining.jpg', 
 '/audio/audiobooks/the-shining.mp3', 
 '/audio/clips/the-shining-clip.mp3', 
 4.7, 2),
('The 5 Second Rule', 'Mel Robbins', 16200, 'Transform your life with one habit', 650.00, 
 '/images/audiobooks/5-second-rule.jpg', 
 '/audio/audiobooks/5-second-rule.mp3', 
 '/audio/clips/5-second-rule-clip.mp3', 
 4.6, 3),
('The Psychology of Money', 'Peter Narrator', 22500, 'Timeless lessons on wealth and greed', 399.00, 
 '/images/audiobooks/psychology-of-money.jpg', 
 '/audio/audiobooks/psychology-of-money.mp3', 
 '/audio/clips/psychology-of-money-clip.mp3', 
 4.8, 4);

-- CARTS
INSERT INTO carts (customer_id) VALUES (1), (2), (3);

-- CART ITEMS
INSERT INTO cart_items (cart_id, audio_id) VALUES
(1, 1), (1, 3), (2, 2), (3, 4);

-- LIBRARY
INSERT INTO library (customer_id, audio_id, last_position, is_completed) VALUES
(1, 1, 1200, FALSE),
(1, 2, 0, TRUE),
(2, 3, 300, FALSE),
(3, 4, 0, FALSE);

-- PAYMENT CARDS
INSERT INTO payment_cards (customer_id, card_number, card_holder_name, expiry_date, cvv, card_type) VALUES
(1, '1234567891234567', 'Yokesh Kumar', '2027-12-31', '123', 'Credit Card'),
(1, '2222333344445555', 'Yokesh Kumar', '2026-06-15', '456', 'Debit Card'),
(2, '9876543210987654', 'Arun Raj', '2028-03-10', '987', 'Credit Card');

-- ORDERS
INSERT INTO orders (customer_id, total_amount, discount_amount, final_amount, payment_status) VALUES
(1, 1850.00, 150.00, 1700.00, 'SUCCESS'),
(2, 650.00, 0.00, 650.00, 'SUCCESS');

-- ORDER ITEMS
INSERT INTO order_items (order_id, audio_id, price) VALUES
(1, 1, 1200.00),
(1, 3, 650.00),
(2, 3, 650.00);
