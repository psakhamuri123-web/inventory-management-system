# Inventory Management System

A portfolio-ready full-stack Inventory Management System built with Java 17, Spring Boot, Spring Security/JWT, JPA/Hibernate, MySQL and Angular.

## Features

- JWT authentication
- Dashboard summary
- Product search and inventory status
- Category and supplier data model
- Stock In / Stock Out / Adjustment transactions
- Low-stock detection
- RESTful APIs
- MySQL persistence
- Docker Compose support
- Angular responsive UI
- Global backend exception handling

## Technology Stack

Backend:
- Java 17
- Spring Boot 3
- Spring MVC / REST
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- MySQL
- Maven
- Lombok

Frontend:
- Angular 18
- TypeScript
- RxJS
- HTML/CSS

## Run locally

### 1. Start MySQL

Option A: install MySQL locally and create `inventory_db`.

Option B:

    docker compose up mysql -d

Default database:
- host: localhost
- port: 3306
- database: inventory_db
- username: root
- password: root

### 2. Start backend

    cd backend
    mvn spring-boot:run

Backend:
    http://localhost:8080

### 3. Start frontend

Install Node.js 20+ and Angular CLI if needed:

    cd frontend
    npm install
    npm start

Frontend:
    http://localhost:4200

### Demo Login

Username: `admin`
Password: `admin123`

The demo user is created automatically by `DataInitializer`.

## API examples

POST `/api/auth/login`

    {
      "username": "admin",
      "password": "admin123"
    }

GET `/api/dashboard/summary`

GET `/api/products`

GET `/api/products?search=keyboard`

GET `/api/products/low-stock`

GET `/api/inventory/transactions`

POST `/api/inventory/transaction`

    {
      "productId": 1,
      "quantity": 5,
      "type": "STOCK_IN",
      "reference": "PO-1001",
      "notes": "Supplier delivery"
    }

## GitHub

Create a new GitHub repository named:

`inventory-management-system`

Then:

    git init
    git add .
    git commit -m "Initial inventory management system"
    git branch -M main
    git remote add origin YOUR_GITHUB_REPOSITORY_URL
    git push -u origin main

## Portfolio / Resume description

Inventory Management System | Java, Spring Boot, Angular, MySQL
- Developed a full-stack inventory platform using Spring Boot REST APIs, Angular and MySQL to manage products, suppliers, categories and stock transactions.
- Implemented JWT-based authentication with Spring Security and role-aware application access.
- Designed JPA/Hibernate entities and transactional stock workflows for stock-in, stock-out and inventory adjustments.
- Built dashboard APIs for product counts, inventory quantities and low-stock monitoring.
- Containerized the backend/database workflow with Docker Compose and documented REST endpoints for development and testing.

## Important

This is an original starter implementation created for portfolio development. Before using it professionally, change the demo credentials, JWT secret, database password, and application configuration.
