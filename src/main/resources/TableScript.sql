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





/* CUSTOMERS */
INSERT INTO customers (username, name, email, password) VALUES
('yokesh01', 'Yokesh Kumar', 'yokesh@example.com', '$2a$10$abcdefghijklmnopqrstuv'),
('arun23', 'Arun Raj', 'arun@example.com', '$2a$10$abcdefghijklmnopqrstuv'),
('megha11', 'Megha Devi', 'megha@example.com', '$2a$10$abcdefghijklmnopqrstuv');



/* AUDIOBOOKS */

INSERT INTO audiobooks (title, narrator, duration, description, price, cover_image, audio_file, short_clip, total_star, author_id) VALUES
('Get Happy', 'Emily Brown', 9900, 'A practical guide to boosting your daily happiness with small, consistent changes.', 199.00,
'/images/Get_Happy.jpg',
'/audio/Get Happy.mp3',
'/sample_audio/Get Happy.mp3',
4.5, 1),

('Habits for Happiness', 'David Lee', 11400, 'Discover simple habits that help you build a happier, more intentional life.', 229.00,
'/images/Habits for Happiness.jpg',
'/audio/Habits for Happiness.mp3',
'/sample_audio/Habits for Happiness.mp3',
4.6, 2),

('Harry Potter', 'Olivia Harris', 30600, 'A magical adventure filled with friendship, courage, and the battle between good and evil.', 299.00,
'/images/Herry Potter.jpg',
'/audio/Herry Potter.mp3',
'/sample_audio/Herry Potter.mp3',
4.9, 3),

('In the Habit', 'Arjun Mehta', 8400, 'An audiobook about understanding how habits are formed and how to reshape them.', 189.00,
'/images/In the Habit.jpg',
'/audio/In the Habit.mp3',
'/sample_audio/In the Habit.mp3', 4.3, 2);



INSERT INTO authors (author_name, author_email, description) VALUES
('Emily Brown', 'emily.brown@example.com', 'Experienced narrator and writer focusing on happiness and personal growth.'),
('David Lee', 'david.lee@example.com', 'Author and habit specialist known for simplifying self-improvement.'),
('Olivia Harris', 'olivia.harris@example.com', 'Fiction writer and narrator with a passion for magical storytelling.'),
('Arjun Mehta', 'arjun.mehta@example.com', 'Author who writes about behaviour, habits, and psychology.'),
('Priya Nair', 'priya.nair@example.com', 'Finance and psychology writer simplifying human-money relationships.'),
('Michael Stone', 'michael.stone@example.com', 'Known for mythological and dramatic storytelling.'),
('Nisha Rao', 'nisha.rao@example.com', 'Crime and thriller author telling gripping investigative stories.'),
('Ryan Cooper', 'ryan.cooper@example.com', 'Spirituality and self-discovery author with a modern approach.'),
('Ananya Sen', 'ananya.sen@example.com', 'Writer focused on decision-making, mental clarity, and life choices.');





/* CART */
INSERT INTO carts (customer_id) VALUES (1), (2), (3);



/* CART_ITEMS */
INSERT INTO cart_items (cart_id, audio_id) VALUES
(1, 1), (1, 3), (2, 2), (3, 4);



/* LIBRARY */

INSERT INTO libraries (customer_id, audio_id, last_position, is_completed) VALUES
(1, 1, 1200, FALSE),
(1, 2, 0, TRUE),
(2, 3, 300, FALSE),
(3, 4, 0, FALSE);



/* PAYMENT CARDS */

INSERT INTO payment_cards (customer_id, card_number, card_holder_name, expiry_date, cvv, card_type) VALUES
(1, '1234567891234567', 'Yokesh Kumar', '2027-12-31', '123', 'Credit Card'),
(1, '2222333344445555', 'Yokesh Kumar', '2026-06-15', '456', 'Debit Card'),
(2, '9876543210987654', 'Arun Raj', '2028-03-10', '987', 'Credit Card');


/* ORDERS */

INSERT INTO orders (customer_id, total_amount, discount_amount, final_amount, payment_status) VALUES
(1, 1850.00, 150.00, 1700.00, 'SUCCESS'),
(2, 650.00, 0.00, 650.00, 'SUCCESS');


/* ORDER ITEMS */

INSERT INTO order_items (order_id, audio_id, price) VALUES
(1, 1, 1200.00),
(1, 3, 650.00),
(2, 3, 650.00);

