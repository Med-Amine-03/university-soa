# 🔐 Authentication Service — Documentation

## 📌 Overview
The **Authentication Service** is responsible for user registration and login. It issues **JWT tokens** that are required for accessing protected endpoints in other services.

---

## 🏗️ Architecture
- **Technology:** Spring Boot
- **Database:** MySQL
- **Port:** `8081`
- **Access via API Gateway:** `http://localhost:8082/auth`

---

## ▶️ Endpoints

### 1️⃣ Register a User
**POST** `/auth/register`

#### Request Body:
```json
{
  "username": "amine",
  "password": "1234"
}
```

#### Response:
```json
{
  "message": "User registered successfully"
}
```

---

### 2️⃣ Login
**POST** `/auth/login`

#### Request Body:
```json
{
  "username": "amine",
  "password": "1234"
}
```

#### Response:
```json
{
  "token": "JWT_TOKEN_HERE"
}
```
⚠️ **Important:** Keep this token — it is required for all protected endpoints.

---

## 🔑 JWT Token Usage
Include the token in the `Authorization` header for all requests:
```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## ⚙️ Environment Variables
```
SPRING_DATASOURCE_URL=jdbc:mysql://mysql_auth:3306/authdb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```

---

## 🐳 Docker Setup
The Authentication Service runs in a Docker container and connects to a MySQL database container.

### MySQL Container:
- **Port Mapping:** `3307 → 3306`
- **Database Name:** `authdb`

### Commands:
```bash
docker compose up -d --build
docker logs auth_service
docker ps | grep auth_service
```

---

## 🧪 Quick Test
```bash
curl -X POST http://localhost:8082/auth/login -H "Content-Type: application/json" -d '{"username":"amine","password":"1234"}'
```

---

## ❗ Common Issues
- **Service unreachable:** Check if container is running:
```bash
docker ps | grep auth_service
```
- **Database connection error:** Verify MySQL logs:
```bash
docker logs mysql_auth
```
