-- ========================
-- OFFERING TYPES
-- ========================
INSERT INTO offeringtypes (id, name) VALUES
(1, 'Tattoo'),
(2, 'Piercing');

-- ========================
-- ARTISTS
-- ========================
INSERT INTO artists (id, name, imageUrl) VALUES
(1, 'Kiefer Santos', 'images/artists/kiefer.jpg'),
(2, 'Maria Lopez', 'images/artists/maria.jpg'),
(3, 'Jay Cruz', 'images/artists/jay.jpg');

-- ========================
-- ARTIST CONTACTS
-- ========================
INSERT INTO artistcontacts (id, artistId, platform, value) VALUES
(1, 1, 'facebook', 'facebook.com/kiefer.ink'),
(2, 1, 'instagram', 'instagram.com/kiefer.ink'),
(3, 2, 'instagram', 'instagram.com/maria.ink'),
(4, 2, 'facebook', 'facebook.com/maria.lopez.tattoo'),
(5, 3, 'instagram', 'instagram.com/jaycruz.ink'),
(6, 3, 'tiktok', 'tiktok.com/@jaycruztattoo');


-- ========================
-- OFFERINGS
-- ========================
INSERT INTO offerings (id, offeringTypeId, name, description, priceRange) VALUES
(1, 1, 'Black & Grey Tattoo', 'Classic black and grey designs, perfect for portraits and shading work.', '₱3000 - ₱15000'),
(2, 1, 'Color Tattoo', 'Full-color tattoos with vibrant ink and custom designs.', '₱4000 - ₱20000'),
(3, 2, 'Ear Piercing', 'Standard ear piercing with sterilized equipment.', '₱800 - ₱2000'),
(4, 2, 'Navel Piercing', 'Trendy belly button piercing with jewelry included.', '₱1500 - ₱2500');

-- ========================
-- PORTFOLIO
-- ========================
INSERT INTO portfolio (id, artistId, title, description, imageUrl) VALUES
(1, 1, 'Dragon Sleeve', 'Full-sleeve dragon tattoo with intricate details.', 'images/portfolio/dragon_sleeve.jpg'),
(2, 1, 'Minimalist Tattoo', 'Clean minimalist design for first-timers.', 'images/portfolio/minimalist.jpg'),
(3, 2, 'Floral Backpiece', 'Colorful floral tattoo covering the back.', 'images/portfolio/floral_backpiece.jpg'),
(4, 3, 'Geometric Tattoo', 'Modern geometric linework tattoo.', 'images/portfolio/geometric.jpg');

-- ========================
-- SETTINGS
-- ========================
INSERT INTO settings (settingKey, settingValue) VALUES
('companyName', 'Kiefer''s Ink Tattoo'),
('email', 'info@kiefersinktattoo.com'),
('phone', '+63 917 555 8888'),
('facebook', 'facebook.com/kiefersinktattoo'),
('instagram', 'instagram.com/kiefersinktattoo'),
('logoUrl', 'images/logo.png'),
('address', '2nd Floor, SM City Manila, Philippines'),
('openHours', 'Mon-Sun, 10:00 AM - 8:00 PM');


-- ========================
-- INQUIRIES
-- ========================
INSERT INTO inquiries (id, offeringId, createdAt, customerName, customerEmail, customerPhone, subject, message) VALUES
(1, 1, '2025-09-13 14:30:00', 'John Doe', 'johndoe@gmail.com', '09171112222', 'Tattoo Inquiry', 'Hi, I’d like to book a black & grey portrait tattoo.'),
(2, 3, '2025-09-12 16:10:00', 'Anna Smith', 'anna.smith@yahoo.com', '09173334444', 'Piercing Appointment', 'Do you accept walk-ins for ear piercing?'),
(3, 2, '2025-09-11 10:20:00', 'Mark Lee', 'mark.lee@gmail.com', '09176667777', 'Color Tattoo Request', 'Looking for a color phoenix tattoo on my arm.');

-- ========================
-- COMPANY CONTACTS
-- ========================
INSERT INTO companycontacts (platform, value) VALUES
('facebook', 'facebook.com/kiefersinktattoo'),
('instagram', 'instagram.com/kiefersinktattoo'),
('email', 'info@kiefersinktattoo.com'),
('phone', '+63 917 555 8888'),
('address', '2nd Floor, SM City Manila, Philippines');
