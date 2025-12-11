# 📘 Course Service — SOAP API & Deployment Guide

## 📌 Prerequisites
- Service runs locally at:  
  **`http://localhost:8083/courseService`**
- Build and run:
  ```bash
  mvn clean package
  java --add-opens=java.base/java.lang=ALL-UNNAMED        --add-opens=java.base/java.lang.reflect=ALL-UNNAMED        -jar target/course-service-1.0.0.jar
  ```
- Recommended tool: **Postman** or **SoapUI** for testing SOAP requests.

---

## 🚀 SOAP API — CourseService

**Base Endpoint:**  
`http://localhost:8083/courseService`

**WSDL:**  
`http://localhost:8083/courseService?wsdl`

### Operations
- **addCourse** — Add a new course
- **updateCourse** — Update an existing course
- **deleteCourse** — Delete a course by ID
- **listCourses** — List all courses
- **getCourse** — Get a course by ID

---

## 🧪 Test with Postman

### ✅ Headers for all requests:
```
Content-Type: text/xml;charset=UTF-8
SOAPAction: "<operation-specific-action>"
```

Example SOAPAction values:
- `http://course.soa.com/CourseEndpoint/addCourseRequest`
- `http://course.soa.com/CourseEndpoint/updateCourseRequest`
- `http://course.soa.com/CourseEndpoint/deleteCourseRequest`
- `http://course.soa.com/CourseEndpoint/listCoursesRequest`
- `http://course.soa.com/CourseEndpoint/getCourseRequest`

---

### 🟩 Add Course
**POST** `http://localhost:8083/courseService`

**Body (raw → XML):**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:c="http://course.soa.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <c:addCourse>
      <course>
        <id>0</id>
        <name>Algorithms</name>
        <teacher>Dr. Smith</teacher>
        <credits>4</credits>
      </course>
    </c:addCourse>
  </soapenv:Body>
</soapenv:Envelope>
```

**Response:**
```xml
<result>
  <success>true</success>
  <message>Created with id=1</message>
</result>
```

---

### 🟩 List Courses
**POST** `http://localhost:8083/courseService`

**Body:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:c="http://course.soa.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <c:listCourses/>
  </soapenv:Body>
</soapenv:Envelope>
```

**Response:**
```xml
<courses>
  <Course>
    <id>1</id>
    <name>Algorithms</name>
    <teacher>Dr. Smith</teacher>
    <credits>4</credits>
  </Course>
</courses>
```

---

### 🟩 Get Course by ID
**Body:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:c="http://course.soa.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <c:getCourse>
      <id>1</id>
    </c:getCourse>
  </soapenv:Body>
</soapenv:Envelope>
```

---

### 🟩 Update Course
**Body:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:c="http://course.soa.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <c:updateCourse>
      <course>
        <id>1</id>
        <name>Advanced Algorithms</name>
        <teacher>Dr. Smith</teacher>
        <credits>5</credits>
      </course>
    </c:updateCourse>
  </soapenv:Body>
</soapenv:Envelope>
```

---

### 🟩 Delete Course
**Body:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:c="http://course.soa.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <c:deleteCourse>
      <id>1</id>
    </c:deleteCourse>
  </soapenv:Body>
</soapenv:Envelope>
```

**Response:**
```xml
<result>
  <success>true</success>
  <message>Deleted</message>
</result>
```

---

### ✅ Notes
- Always use **POST** for SOAP operations.
- Use the correct `SOAPAction` header for each method.
- The service uses an embedded H2 database; no external DB setup required.
