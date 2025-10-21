-- Replace 'your_database_name' with your actual database name
SET FOREIGN_KEY_CHECKS = 0;

-- Generate truncate statements for all tables
SELECT CONCAT('TRUNCATE TABLE `', TABLE_NAME, '`;')
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'kiefersink_db';

SET FOREIGN_KEY_CHECKS = 1;