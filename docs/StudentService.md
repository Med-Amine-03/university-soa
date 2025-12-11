
# 📘 Student Service — REST API & Deployment Guide


## 📌 Prerequisites

- The service runs locally on: **`http://localhost:8082/students`**
- Recommended tools: **Postman**.

---

## 🚀 REST API — StudentService

**Base URL:** `http://localhost:8082/students`

### Endpoints

- **GET** `/students` — List all students
- **GET** `/students/:id` — Get a student by ID
- **POST** `/students` — Create a student
- **PUT** `/students/:id` — Update a student by ID
- **DELETE** `/students/:id` — Delete a student by ID


---

## 🧪 Test with Postman

### 🟩 List all students
**GET** `http://localhost:8082/students`

**Response**
```json
[
  { "_id": "674f1...", "name": "Alice", "age": 22, "department": "Math" },
  { "_id": "674f2...", "name": "Bob", "age": 20, "department": "Physics" }
]
```

---

### 🟩 Get a student by ID
**GET** `http://localhost:8082/students/6939bd3c0400197e1cfe0090`

**Response**
```json
{ "_id": "*6939bd3c0400197e1cfe0090", "name": "Alice", "age": 22, "department": "Math" }
```



### 🟩 Create a student
**POST** `http://localhost:8082/students`


**Body**
```json
{
  "name": "rannou1",
  "age": 22,
  "department": "CS"
}
```

**Response (201)**s
```json 
{
  "_id": "6939bd3c0400197e1cfe0090",
  "name": "rannou1",
  "age": 22,
  "department": "CS"
}
```


---

### 🟩 Update a student
**PUT** `http://localhost:8082/students/6939bd3c0400197e1cfe0090`

**Body**
```json
{
  "name": "ranou1",
  "age": 20,
  "department": "CS"
}
```

**Response**
```json
{
  "_id": "6750...",
  "name": "ranou1",
  "age": 20,
  "department": "CS"
}
```

---

### 🟩 Delete a student
**DELETE** `http://localhost:4000/students/6939bd3c0400197e1cfe0090`

**Response**
```json
{ "message": "Student deleted" }
```