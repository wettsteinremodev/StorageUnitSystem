-- Create database storage_mangagement
DROP DATABASE IF EXISTS storage_management;
CREATE DATABASE IF NOT EXISTS storage_management;
USE storage_management;

/*
-- Create table: User
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(50)
);

-- Create table: StorageUnit
CREATE TABLE storage_units (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    size_in_m2 DOUBLE,
    price_per_month DOUBLE,
    is_available BOOLEAN NOT NULL
);

-- Create Table: Rented
CREATE TABLE rented (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    storage_unit_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (storage_unit_id) REFERENCES storage_units(id) ON DELETE CASCADE
);

*/