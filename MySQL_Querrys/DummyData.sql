USE storage_management;

-- Clear existing data
DELETE FROM rented;
DELETE FROM storage_unit;
DELETE FROM app_user;

-- Insert Users
INSERT INTO app_user (id, user_name, email, password) VALUES
(1, 'Alice', 'alice@example.com', 'password123'),
(2, 'Bob', 'bob@example.com', 'secret456'),
(3, 'Charlie', 'charlie@example.com', 'charliePass'),
(4, 'Diana', 'diana@example.com', 'dianaSecure'),
(5, 'Ethan', 'ethan@example.com', 'ethanPass1'),
(6, 'Fiona', 'fiona@example.com', 'fionaPass1'),
(7, 'George', 'george@example.com', 'georgePass1'),
(8, 'Hannah', 'hannah@example.com', 'hannahPass1'),
(9, 'Ivan', 'ivan@example.com', 'ivanPass1'),
(10, 'Julia', 'julia@example.com', 'juliaPass1');

-- Insert Storage Units
INSERT INTO storage_unit (id, name, size_in_m2, price_per_month, is_available) VALUES
(1, 'Unit A', 10.0, 100.00, TRUE),
(2, 'Unit B', 20.0, 150.00, TRUE),
(3, 'Unit C', 15.5, 120.00, TRUE),
(4, 'Unit D', 25.0, 180.00, TRUE),
(5, 'Unit E', 30.0, 200.00, TRUE),
(6, 'Unit F', 50.0, 300.00, TRUE),
(7, 'Unit G', 5.0, 80.00, TRUE),
(8, 'Unit H', 60.0, 350.00, TRUE),
(9, 'Unit I', 45.0, 290.00, TRUE),
(10, 'Unit J', 12.0, 110.00, TRUE);

-- Insert Rentals
INSERT INTO rented (id, user_id, storage_unit_id, start_date, end_date) VALUES
(1, 1, 1, '2023-05-01', '2024-05-01'),
(2, 1, 2, '2024-06-01', '2025-06-01'),
(3, 2, 3, '2024-01-01', '2025-01-01'),
(4, 2, 4, '2025-02-01', '2026-02-01'),
(5, 3, 5, '2024-07-15', '2025-07-15'),
(6, 3, 6, '2025-08-01', '2026-08-01'),
(7, 4, 7, '2023-09-10', '2024-09-10'),
(8, 5, 8, '2024-03-01', '2025-03-01'),
(9, 5, 9, '2025-04-01', '2026-04-01'),
(10, 6, 10, '2024-01-20', '2025-01-20'),
(11, 7, 1, '2023-11-05', '2024-11-05'),
(12, 7, 2, '2024-12-05', '2025-12-05'),
(13, 8, 3, '2024-06-10', '2025-06-10'),
(14, 9, 4, '2025-01-01', '2026-01-01'),
(15, 9, 5, '2026-01-02', '2027-01-02'),
(16, 10, 6, '2024-04-01', '2025-04-01'),
(17, 10, 7, '2025-05-01', '2026-05-01'),
(18, 10, 8, '2026-06-01', '2027-06-01');
