# 📘 University SOA — Usage Manual (Updated)

## 🏗️ Architecture Overview
```
                ┌──────────────────┐
                │   API Gateway    │  ← Port 8082
                └────────┬─────────┘
    ┌───────────────────────────────────────────────────────────────┐
    │                     │          |               │              │
┌───────┐          ┌─────────────┐   |     ┌─────────────┐   ┌─────────────┐
│ Auth  │          │ Student Svc │   |     │ Course Svc  │   │ Billing Svc │
│8081   │          │ 4000        │   |     │ 8083 (H2)   │   │ 8085        │
└───────┘          └─────────────┘   |     └─────────────┘   └─────────────┘
                                     |                      
                              ┌───────────────┐
                              │ Grade Svc     │                 
                              │ 8084 (MongoDB)│                 
                              └───────────────┘



┌──────────┐  
│ MySQL    │ ← For Auth Service  
│ 3307 →3306│  
└──────────┘                 
                              
┌──────────┐  
│ MongoDB  │ ← For Student & Grade Services  
│ 27017    │  
└──────────┘  
┌──────────┐  
│ PostgreSQL│ ← For Billing Service
│ 5657     │  
└──────────┘  
```

---

## 🚀 1. Starting the Project

### 🔧 Requirements
- Docker
- Docker Compose
- Postman or Curl

### ▶️ Start all services
From the project root:
```bash
docker compose up -d --build
```

### Services started:
| Service          | Port       | Technology              |
|-----------------|-----------|-------------------------|
| API Gateway     | 8082      | Spring Cloud Gateway    |
| Auth Service    | 8081      | Spring Boot + MySQL     |
| Student Service | 4000      | Node.js + MongoDB       |
| Course Service  | 8083      | Spring Boot + H2 DB     |
| Billing Service | 8085      | Spring Boot + PostgreSQL|
| Grade Service   | 8084      | Node.js + MongoDB       |
| MySQL           | 3307 → 3306| Database for Auth       |
| MongoDB         | 27017     | Database for Students & Grades |
| PostgreSQL      | 5657      | Database for Billing    |

---

## 📡 2. API Usage
All services must be accessed through the API Gateway:

👉 **Base URL**
```
http://localhost:8082
```

---

### 🔐 Auth Service
**Base URL**
```
http://localhost:8082/auth
```

#### 📝 Register
**POST** `/auth/register`
```json
{
  "username": "amine",
  "password": "1234"
}
```

#### 📝 Login
**POST** `/auth/login`
**Response:**
```json
{
  "token": "JWT_TOKEN_HERE"
}
```
⚠️ Keep this token — it is required for all protected endpoints.

---

### 🧑‍🎓 Student Service
**Base URL**
```
http://localhost:8082/students
```
⚠️ Requires JWT token

#### 📝 Get all students
**GET** `/students`
```
Authorization: Bearer TOKEN
```

#### 📝 Add a student
**POST** `/students`
```
Authorization: Bearer TOKEN
Content-Type: application/json
```
**Body:**
```json
{
  "name": "Karim",
  "email": "karim@gmail.com",
  "age": 21
}
```

---

### 📚 Course Service
**Base URL**
```
http://localhost:8082/courses
```
⚠️ Requires JWT token

#### 📝 Get all courses
**GET** `/courses`

#### 📝 Add a course
**POST** `/courses`
```json
{
  "title": "Software Architecture",
  "credits": 3
}
```

---

### 💳 Billing Service
**Base URL**
```
http://localhost:8082/billing
```
⚠️ Requires JWT token

#### 📝 Get all bills
**GET** `/billing`

#### 📝 Create a bill
**POST** `/billing`
```json
{
  "studentId": "12345",
  "amount": 500
}
```

---

### 🏅 Grade Service
**Base URL**
```
http://localhost:8082/grades
```
⚠️ Requires JWT token

#### 📝 Get all grades
**GET** `/grades`

#### 📝 Add a grade
**POST** `/grades`
```json
{
  "studentId": "12345",
  "courseId": "67890",
  "grade": "A"
}
```

---

## 🔧 3. Environment Variables
### Auth Service
```
SPRING_DATASOURCE_URL=jdbc:mysql://mysql_auth:3306/authdb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```

### Student Service
```
MONGO_URI=mongodb://mongodb:27017/studentsdb
PORT=4000
```

### Grade Service
```
MONGO_URI=mongodb://mongodb:27017/gradesdb
PORT=8084
```

### Billing Service
```
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres_billing:5657/billingdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

---

## 🐳 4. Docker Management Commands
✔ Check container status:
```bash
docker ps
```
✔ Check logs:
```bash
docker logs gateway
docker logs student_service
docker logs auth_service
docker logs course_service
docker logs billing_service
docker logs grade_service
```
✔ Restart a service:
```bash
docker compose restart gateway
```

---

## 🧪 5. Quick API Tests
### 1️⃣ Test Login
```bash
curl -X POST http://localhost:8082/auth/login -H "Content-Type: application/json" -d '{"username":"amine","password":"1234"}'
```

### 2️⃣ Test Student API
```bash
curl http://localhost:8082/students -H "Authorization: Bearer YOUR_JWT"
```

### 3️⃣ Test Course API
```bash
curl http://localhost:8082/courses -H "Authorization: Bearer YOUR_JWT"
```

---

## ❗ 6. Common Issues & Fixes
❌ Service unreachable  
✔ Ensure Gateway route:
```
uri: http://SERVICE_NAME:PORT
```
✔ Ensure container is running:
```bash
docker ps | grep SERVICE_NAME
```
✔ Check database connection:
```bash
docker logs mongodb
docker logs mysql_auth
docker logs postgres_billing
```

---

## 📦 7. Stop all services
```bash
docker compose down
```
