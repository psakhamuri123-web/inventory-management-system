CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

-- The Spring Boot application creates/updates tables automatically.
-- This file is provided for project documentation and initial database setup.

CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL UNIQUE,
  description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS suppliers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(200),
  phone VARCHAR(50),
  address VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  sku VARCHAR(100) NOT NULL UNIQUE,
  name VARCHAR(200) NOT NULL,
  description VARCHAR(1000),
  price DECIMAL(12,2) NOT NULL,
  quantity INT NOT NULL,
  reorder_level INT NOT NULL,
  category_id BIGINT,
  supplier_id BIGINT,
  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE IF NOT EXISTS inventory_transactions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_id BIGINT NOT NULL,
  type VARCHAR(30) NOT NULL,
  quantity INT NOT NULL,
  reference VARCHAR(200),
  notes VARCHAR(1000),
  transaction_date DATETIME NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products(id)
);
