-- Sample Data for Audible Database
USE audible_db;

-- Insert sample authors
INSERT INTO authors (author_name, author_email, description) VALUES
('J.K. Rowling', 'jkrowling@example.com', 'British author, best known for the Harry Potter series'),
('Stephen King', 'sking@example.com', 'American author of horror, supernatural fiction, suspense, and fantasy novels'),
('Michael Pollan', 'mpollan@example.com', 'American author, journalist, and activist'),
('Morgan Housel', 'mhousel@example.com', 'Partner at The Collaborative Fund and author of The Psychology of Money'),
('Mel Robbins', 'mrobbins@example.com', 'American motivational speaker and author');

-- Insert sample customers (passwords are hashed with BCrypt - these are placeholders)
-- Note: In production, passwords should be hashed using BCryptPasswordEncoder
-- Example: $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy = "password"
INSERT INTO customers (username, name, email, password) VALUES
('john_doe', 'John Doe', 'john.doe@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('jane_smith', 'Jane Smith', 'jane.smith@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('bob_wilson', 'Bob Wilson', 'bob.wilson@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('alice_brown', 'Alice Brown', 'alice.brown@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('charlie_davis', 'Charlie Davis', 'charlie.davis@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('diana_miller', 'Diana Miller', 'diana.miller@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('edward_jones', 'Edward Jones', 'edward.jones@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('fiona_taylor', 'Fiona Taylor', 'fiona.taylor@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

-- Insert sample audiobooks
-- Using working placeholder image service and sample audio URLs
INSERT INTO audiobooks (title, narrator, duration, description, price, cover_image, src, short_clip, total_star, author_id) VALUES
('Get Happy', 'John Narrator', '5:30:00', 'A journey to finding happiness in everyday life. This audiobook explores practical ways to cultivate joy and contentment.', 350.00, 'https://picsum.photos/300/300?random=1', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3', 5.00, 5),
('Caffeine', 'Selena Voice', '3:45:00', 'An exploration of caffeine and its effects on the human body and mind. Discover the science behind your daily cup of coffee.', 599.00, 'https://picsum.photos/300/300?random=2', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3', 4.00, 3),
('The Psychology of Money', 'Peter Narrator', '6:15:00', 'Timeless lessons on wealth, greed, and happiness. Learn how to think about money and make better financial decisions.', 399.00, 'https://picsum.photos/300/300?random=3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3', 4.50, 4),
('Take Control of your Life', 'Isabela Voice', '7:20:00', 'Mel Robbins helps you tackle the single biggest obstacle you face: fear. Features powerful life coaching sessions.', 950.00, 'https://picsum.photos/300/300?random=4', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3', 3.00, 5),
('Harry Potter and the Philosopher\'s Stone', 'Jim Dale', '8:45:00', 'The first book in the Harry Potter series. Follow Harry as he discovers he is a wizard and attends Hogwarts School.', 1200.00, 'https://picsum.photos/300/300?random=5', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3', 4.80, 1),
('The Shining', 'Campbell Scott', '15:30:00', 'A classic horror novel about a writer who becomes the caretaker of an isolated hotel during the winter.', 899.00, 'https://picsum.photos/300/300?random=6', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3', 4.70, 2),
('It', 'Steven Weber', '44:50:00', 'Seven adults return to their hometown to confront a nightmare they had first stumbled on as teenagers.', 1299.00, 'https://picsum.photos/300/300?random=7', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3', 4.90, 2),
('The Omnivore\'s Dilemma', 'Scott Brick', '16:20:00', 'A natural history of four meals. Explores the question: What should we have for dinner?', 799.00, 'https://picsum.photos/300/300?random=8', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3', 4.30, 3),
('Harry Potter and the Chamber of Secrets', 'Jim Dale', '9:15:00', 'The second book in the Harry Potter series. Harry returns to Hogwarts for his second year.', 1200.00, 'https://picsum.photos/300/300?random=9', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3', 4.75, 1),
('The 5 Second Rule', 'Mel Robbins', '4:30:00', 'Transform your life, work, and confidence with everyday courage. Learn the simple rule that can change everything.', 650.00, 'https://picsum.photos/300/300?random=10', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3', 4.60, 5),
('Pet Sematary', 'Michael C. Hall', '12:45:00', 'A horror novel about a family that discovers a mysterious burial ground with the power to bring the dead back to life.', 849.00, 'https://picsum.photos/300/300?random=11', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-11.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-11.mp3', 4.40, 2),
('In Defense of Food', 'Scott Brick', '5:50:00', 'An eater\'s manifesto. Learn how to make better food choices for your health and the environment.', 599.00, 'https://picsum.photos/300/300?random=12', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-12.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-12.mp3', 4.20, 3),
('Harry Potter and the Prisoner of Azkaban', 'Jim Dale', '12:00:00', 'The third book in the Harry Potter series. Harry learns about Sirius Black and the truth about his parents.', 1200.00, 'https://picsum.photos/300/300?random=13', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-13.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-13.mp3', 4.85, 1),
('Carrie', 'Sissy Spacek', '7:30:00', 'The first published novel by Stephen King. A high school girl with telekinetic powers seeks revenge on her tormentors.', 699.00, 'https://picsum.photos/300/300?random=14', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-14.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-14.mp3', 4.50, 2),
('The Botany of Desire', 'Scott Brick', '8:15:00', 'A plant\'s-eye view of the world. Explores how plants have evolved to satisfy human desires.', 749.00, 'https://picsum.photos/300/300?random=15', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-15.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-15.mp3', 4.10, 3),
('Kick Ass with Mel Robbins', 'Mel Robbins', '3:45:00', 'A motivational guide to taking action and achieving your goals. Practical advice for everyday life.', 550.00, 'https://picsum.photos/300/300?random=16', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-16.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-16.mp3', 4.55, 5),
('The Stand', 'Grover Gardner', '47:30:00', 'A post-apocalyptic horror fantasy novel about a pandemic that wipes out most of the world\'s population.', 1499.00, 'https://picsum.photos/300/300?random=17', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-17.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-17.mp3', 4.95, 2),
('Harry Potter and the Goblet of Fire', 'Jim Dale', '20:45:00', 'The fourth book in the Harry Potter series. Harry competes in the dangerous Triwizard Tournament.', 1200.00, 'https://picsum.photos/300/300?random=18', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-18.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-18.mp3', 4.90, 1),
('Food Rules', 'Scott Brick', '2:30:00', 'An eater\'s manual. Simple, memorable rules for eating wisely.', 399.00, 'https://picsum.photos/300/300?random=19', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-19.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-19.mp3', 4.00, 3),
('The High 5 Habit', 'Mel Robbins', '5:15:00', 'Take control of your life with one simple habit. Transform your mindset and achieve your goals.', 699.00, 'https://picsum.photos/300/300?random=20', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-20.mp3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-20.mp3', 4.65, 5);

-- Insert sample carts for some customers
INSERT INTO carts (customer_id) VALUES
(1), (2), (3);

-- Insert sample cart items
INSERT INTO cart_items (cart_id, audio_id) VALUES
(1, 1), (1, 3),
(2, 2), (2, 4),
(3, 5);

-- Insert sample library entries (purchased books)
INSERT INTO library (customer_id, audio_id, last_position, is_completed) VALUES
(1, 1, 0, FALSE),
(1, 3, 1200, FALSE),
(2, 2, 0, FALSE),
(3, 5, 3600, FALSE),
(3, 6, 0, FALSE);

-- Insert sample payment cards
INSERT INTO payment_cards (customer_id, card_number, card_holder_name, expiry_date, cvv, card_type) VALUES
(1, '1234567891234567', 'John Doe', '2024-09-14', '123', 'Credit Card'),
(1, '4382636463722123', 'John Doe', '2027-12-31', '456', 'Credit Card'),
(2, '9876543210987654', 'Jane Smith', '2025-06-30', '789', 'Debit Card'),
(3, '1111222233334444', 'Bob Wilson', '2026-03-15', '321', 'Credit Card');

