-- ========================
-- ARTISTS
-- ========================
INSERT INTO artists (name, imageUrl) VALUES
('Jek Valencia', 'jek_valencia.jpg'),
('Dhens Valencia', 'dhens_valencia.jpg'),
('Arman Walin', 'arman_walin.jpg');
-- ========================
-- ARTIST CONTACTS
-- ========================
INSERT INTO artistcontacts (artistId, platform, handle, url) VALUES
(1, 'facebook', 'Jek Valencia', 'https://www.facebook.com/rosewester18'),
(1, 'instagram', '@jek.kiefersinktattoo', 'https://www.instagram.com/jek.kiefersinktattoo/'),
(1, 'tiktok', '@jek.kiefersinktat', 'https://www.tiktok.com/@jek.kiefersinktat'),

(2, 'facebook', 'Dhens Valencia', 'https://www.facebook.com/alyssadhens18'),
(2, 'instagram', '@dhens.kiefersink', 'https://www.instagram.com/dhens.kiefersink/'),

(3, 'facebook', 'Arman Walin', 'https://www.facebook.com/profile.php?id=61581541725835'),
(3, 'instagram', '@rman.kiefersink', 'https://www.instagram.com/rman.kiefersink/'),
(3, 'tiktok', '@arman.kiefersinkt', 'https://www.tiktok.com/@arman.kiefersinkt');

-- ========================
-- CATEGORIES
-- ========================
INSERT INTO categories (name) VALUES
('Tattoo'),
('Piercing');

-- ========================
-- OFFERINGS
-- ========================
INSERT INTO offerings (name, categoryId, description, priceRange, imageUrl) VALUES
('Black & Grey Tattoo', 1, 'Classic black and grey designs with smooth shading and timeless contrast.', '₱3,000 - ₱30,000', 'blackngray.jpg'),
('Color Tattoo', 1, 'Full-color tattoos with vibrant ink and detailed blending.', '₱4,000 - ₱40,000', 'colored.jpg'),
('Watercolor Tattoo', 1, 'Soft, artistic watercolor designs that mimic paint brush effects.', '₱5,000 - ₱45,000', 'watercolor.jpg'),
('Minimalist Tattoo', 1, 'Clean, elegant linework and simple forms for subtle expression.', '₱1,500 - ₱18,000', 'minimalist.jpg'),
('Realism Tattoo', 1, 'Hyper-detailed, lifelike portraits and realistic imagery.', '₱6,000 - ₱50,000', 'realism.jpg'),
('Japanese Tattoo', 1, 'Traditional Japanese motifs with bold outlines and deep symbolism.', '₱8,000 - ₱60,000', 'japanese.png'),
('Fine Line Tattoo', 1, 'Delicate, precise linework emphasizing subtle beauty and detail.', '₱2,500 - ₱15,000', 'fineline.jpeg'),
('Tribal Tattoo', 1, 'Bold, black patterns inspired by cultural and symbolic designs.', '₱4,000 - ₱30,000', 'tribal.jpg'),
('Script Tattoo', 1, 'Typography and lettering pieces expressing words, quotes, or names.', '₱2,000 - ₱10,000', 'script.jpg'),

('Ear Lobe Piercing', 2, 'A simple and classic piercing on the soft lower part of the ear. Perfect for first-timers.', '₱500 - ₱1,000', 'earlobe.jpg'),
('Conch Piercing', 2, 'A stylish piercing placed in the middle part of the ear’s cartilage.', '₱1,000 - ₱2,000', 'conch.jpg'),
('Helix Piercing', 2, 'A trendy piercing on the upper cartilage of the ear, known for its versatility in jewelry options.', '₱800 - ₱1,800', 'helix.png'),
('Nostril Piercing', 2, 'Stylish nostril piercings done with precision and care.', '₱800 - ₱2,500', 'nostril.jpg'),
('Eyebrow Piercing', 2, 'Expressive brow piercings that enhance your unique style.', '₱1,000 - ₱3,000', 'eyebrow.jpg'),
('Lip Piercing', 2, 'Beautiful lip and labret piercings with safe aftercare guidance.', '₱1,200 - ₱3,500', 'lip.jpg'),
('Tongue Piercing', 2, 'Professional tongue piercings with sterilized equipment and expert care.', '₱1,500 - ₱4,000', 'tongue.jpg'),
('Belly Piercing', 2, 'Stylish navel piercings with jewelry options for every look.', '₱1,000 - ₱3,000', 'belly.jpg'),
('Industrial Piercing', 2, 'Double ear cartilage piercings connected by a single barbell.', '₱1,500 - ₱4,000', 'industrial.jpg'),
('Daith Piercing', 2, 'Unique inner ear piercing known for both style and migraine relief.', '₱1,200 - ₱3,500', 'daith.jpg'),
('Septum Piercing', 2, 'Bold central nose piercing with customizable jewelry options.', '₱1,500 - ₱3,000', 'septum.jpg');

-- ========================
-- PORTFOLIO
-- ========================
INSERT INTO portfolios (artistId, title, description, imageUrl, offeringId, featured) VALUES
(2, 'Wolf Forearm Tattoo', 'Eyes like ice, heart like fire. Tattoo by @dhens.kiefersink.', 'gallery_tattoo1.jpg', 1, 1),
(1, 'Minimalist Forearm Tattoo', 'Tattoos are time travel etched in skin.', 'gallery_tattoo2.jpg', 4, 0),
(1, 'Ink Flow Dragon', 'A sumi-e inspired dragon tattoo flowing across the shoulder and arm.', 'gallery_tattoo3.jpg', 1, 1),
(2, 'Dog Portrait', 'A realistic dog portrait framed in a minimalist box, capturing the warmth and joy of a beloved pet.', 'gallery_tattoo4.jpg', 5, 1),
(2, 'Rooted Resilience', 'A gnarled, leafless tree tattoo extending across the upper arm and shoulder, symbolizing endurance.', 'gallery_tattoo5.jpg', 1, 0),
(1, 'Minimalist Butterflies', 'Tiny wings, big dreams 🦋', 'gallery_tattoo6.jpg', 4, 0),
(2, 'Minimalist Wolf', 'Stay wild, stay free, stay fierce.', 'gallery_tattoo7.jpg', 4, 0),
(3, 'Koi Forearm Tattoo', 'Balance. Harmony. Flow.', 'gallery_tattoo8.jpg', 2, 0),
(3, 'Fractured Wings', 'A sharp, symmetrical tattoo centered on the upper back, symbolizing transformation and strength.', 'gallery_tattoo9.jpg', 4, 0),
(3, 'Shoshin (初心)', 'A minimalist tattoo featuring the Japanese kanji "初心" (Shoshin), meaning "beginner’s mind."', 'gallery_tattoo10.jpg', 9, 1);

-- ========================
-- SETTINGS
-- ========================
INSERT INTO settings (settingKey, settingValue) VALUES
('companyName', 'Kiefer''s Ink Tattoo'),
('logoUrl', 'logo_white.png'),
('openHours', 'Mon-Sun, 10:00 AM - 8:00 PM'),
('address', '3rd flr, 189 Victor Javier Rosario Pasig City, Pasig, Philippines');


-- ========================
-- INQUIRIES
-- ========================
INSERT INTO inquiries (customerName, customerEmail, customerPhone, subject, message) VALUES
('John Doe', 'johndoe@gmail.com', '09171112222', 'Tattoo Inquiry', 'Hi, I’d like to book a black & grey portrait tattoo.'),
('Anna Smith', 'anna.smith@yahoo.com', '09173334444', 'Piercing Appointment', 'Do you accept walk-ins for ear piercing?'),
('Mark Lee', 'mark.lee@gmail.com', '09176667777', 'Color Tattoo Request', 'Looking for a color phoenix tattoo on my arm.');

-- ========================
-- COMPANY CONTACTS
-- ========================
INSERT INTO companycontacts (platform, handle, url) VALUES
('facebook', 'Kiefer''s INK Tattoo', 'https://www.facebook.com/dhensvalenciaworks'),
('instagram', '@kiefersinktattoo', 'https://instagram.com/kiefersinktattoo'),
('tiktok', '@kiefersinktattoo', 'https://www.tiktok.com/@kiefersinktattoo'),
('email', 'info@kiefersinktattoo.com', 'mailto:info@kiefersinktattoo.com'),
('phone', '09955545887', '')
