# Student Management System - Spring Boot REST API

A complete, production-ready **Student Management REST API** built with **Spring Boot 3**, **Spring Data JPA**, and **MySQL**, adhering to REST architectural standards and modern Java best practices.

---

## 📋 Table of Contents
- [Assignment Overview](#-assignment-overview)
- [Tech Stack](#-tech-stack)
- [Project Directory Structure](#-project-directory-structure)
- [Entity Attributes](#-entity-attributes)
- [REST API Endpoints](#-rest-api-endpoints)
- [Database Setup (MySQL)](#-database-setup-mysql)
- [How to Run the Application](#-how-to-run-the-application)
- [Testing with Postman (Deliverable Screenshots Guide)](#-testing-with-postman-deliverable-screenshots-guide)
- [Exception Handling & Status Codes](#-exception-handling--status-codes)
- [Pushing to GitHub (Deliverable Guide)](#-pushing-to-github-deliverable-guide)

---

## 📌 Assignment Overview

This project satisfies all requirements specified in the college assignment:
1. **Entity**: `Student` entity with `id`, `name`, `email`, `course`, and `age`.
2. **Operations**: Full CRUD operations + course-based filtering.
3. **Database**: MySQL integrated with Spring Data JPA.
4. **Exception Handling**: Global controller advice returning clean JSON errors with appropriate HTTP status codes (e.g., `404 Not Found`, `400 Bad Request`, `201 Created`).
5. **Testing**: Ready-to-import Postman collection.

---

## 💻 Tech Stack

- **Language:** Java 17 / 21
- **Framework:** Spring Boot 3.3.5
- **ORM / Persistence:** Spring Data JPA / Hibernate
- **Database:** MySQL 8.x
- **Build Tool:** Maven
- **Validation:** Jakarta Validation (Hibernate Validator)
- **API Testing:** Postman

---

## 📂 Project Directory Structure

```text
student-management-system/
├── pom.xml
├── database.sql                                      # MySQL Database & Table schema script
├── README.md
├── .gitignore
├── postman/
│   └── Student_Management_System_API.postman_collection.json  # Import directly into Postman
└── src/
    ├── main/
    │   ├── java/com/college/studentmanagement/
    │   │   ├── StudentManagementApplication.java     # Main Spring Boot Application entry point
    │   │   ├── controller/
    │   │   │   └── StudentController.java            # REST API Controller (/api/students)
    │   │   ├── entity/
    │   │   │   └── Student.java                      # JPA Entity
    │   │   ├── exception/
    │   │   │   ├── ErrorDetails.java                 # Formatted error payload
    │   │   │   ├── GlobalExceptionHandler.java       # Centralized exception handling
    │   │   │   └── ResourceNotFoundException.java   # 404 Custom Exception
    │   │   ├── repository/
    │   │   │   └── StudentRepository.java            # Spring Data JPA Repository
    │   │   └── service/
    │   │       ├── StudentService.java               # Service Interface
    │   │       └── impl/
    │   │           └── StudentServiceImpl.java       # Service Implementation
    │   └── resources/
    │       └── application.properties               # MySQL & JPA configuration
    └── test/
        └── java/com/college/studentmanagement/
            └── StudentManagementApplicationTests.java
```

---

## 🏷️ Entity Attributes

| Attribute | Type | Description | Annotations |
| :--- | :--- | :--- | :--- |
| `id` | `Long` | Primary Key (Auto-generated) | `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)` |
| `name` | `String` | Student name | `@NotBlank`, `@Column(nullable = false)` |
| `email` | `String` | Student email (Unique) | `@NotBlank`, `@Email`, `@Column(nullable = false, unique = true)` |
| `course`| `String` | Enrolled course | `@NotBlank`, `@Column(nullable = false)` |
| `age` | `Integer`| Student age | `@NotNull`, `@Min(1)`, `@Column(nullable = false)` |

---

## 🚀 REST API Endpoints

| HTTP Method | Endpoint | Description | Success Status |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/students` | Add a new student | `201 Created` |
| **GET** | `/api/students` | Retrieve all students | `200 OK` |
| **GET** | `/api/students/{id}` | Retrieve student by ID | `200 OK` (or `404 Not Found`) |
| **PUT** | `/api/students/{id}` | Update student details | `200 OK` (or `404 Not Found`) |
| **DELETE** | `/api/students/{id}` | Delete a student | `200 OK` (or `404 Not Found`) |
| **GET** | `/api/students/course/{course}` | Retrieve students by course | `200 OK` |

---

## 🗄️ Database Setup (MySQL)

### Step 1: Start MySQL Server
Ensure MySQL Server is installed and running on port `3306`.

### Step 2: Configure `application.properties`
Check `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
> **Note:** If your MySQL root password is not `root`, replace `YOUR_MYSQL_PASSWORD` with your actual MySQL password.
> `createDatabaseIfNotExist=true` will automatically create the `student_db` database for you!

### Step 3 (Optional): Manual Database Creation
You can run the included `database.sql` script in MySQL Workbench or MySQL Command Line:
```sql
CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;
```

---

## 🏃 How to Run the Application

### Option A: Using IntelliJ IDEA / Eclipse / STS
1. Open your IDE.
2. Select **File -> Open** (or **Import as Maven Project**) and choose the `student-management-system` folder.
3. Wait for Maven dependencies to download.
4. Locate `src/main/java/com/college/studentmanagement/StudentManagementApplication.java`.
5. Right-click and choose **Run 'StudentManagementApplication'**.
6. The application starts at: `http://localhost:8080`.

### Option B: Using Command Line (Maven)
In the project root directory, run:
```bash
mvn clean spring-boot:run
```

---

## 📮 Testing with Postman (Deliverable Screenshots Guide)

You need to take screenshots of the requests and responses in Postman for your assignment submission.

### Quick Import:
1. Open **Postman**.
2. Click **Import** (top left).
3. Select the file: `postman/Student_Management_System_API.postman_collection.json`.
4. All endpoints and test payloads are pre-configured!

---

### Screenshot 1: `POST /api/students` (Add New Student)
- **Method:** `POST`
- **URL:** `http://localhost:8080/api/students`
- **Headers:** `Content-Type: application/json`
- **Request Body (raw JSON):**
  ```json
  {
      "name": "Alice Johnson",
      "email": "alice.j@example.com",
      "course": "Computer Science",
      "age": 21
  }
  ```
- **Response:** `201 Created`
  ```json
  {
      "id": 1,
      "name": "Alice Johnson",
      "email": "alice.j@example.com",
      "course": "Computer Science",
      "age": 21
  }
  ```

---

### Screenshot 2: `GET /api/students` (Retrieve All Students)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/students`
- **Response:** `200 OK`
  ```json
  [
      {
          "id": 1,
          "name": "Alice Johnson",
          "email": "alice.j@example.com",
          "course": "Computer Science",
          "age": 21
      }
  ]
  ```

---

### Screenshot 3: `GET /api/students/{id}` (Retrieve by ID - Success)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/students/1`
- **Response:** `200 OK`
  ```json
  {
      "id": 1,
      "name": "Alice Johnson",
      "email": "alice.j@example.com",
      "course": "Computer Science",
      "age": 21
  }
  ```

---

### Screenshot 4: `GET /api/students/{id}` (Student Not Found - 404)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/students/999`
- **Response:** `404 Not Found`
  ```json
  {
      "timestamp": "2026-11-12T15:30:00.123456",
      "status": 404,
      "error": "Not Found",
      "message": "Student not found with id: 999",
      "path": "/api/students/999"
  }
  ```

---

### Screenshot 5: `PUT /api/students/{id}` (Update Student)
- **Method:** `PUT`
- **URL:** `http://localhost:8080/api/students/1`
- **Headers:** `Content-Type: application/json`
- **Request Body (raw JSON):**
  ```json
  {
      "name": "Alice J. Williams",
      "email": "alice.williams@example.com",
      "course": "Data Science",
      "age": 22
  }
  ```
- **Response:** `200 OK`
  ```json
  {
      "id": 1,
      "name": "Alice J. Williams",
      "email": "alice.williams@example.com",
      "course": "Data Science",
      "age": 22
  }
  ```

---

### Screenshot 6: `GET /api/students/course/{course}` (Retrieve by Course)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/students/course/Data Science`
- **Response:** `200 OK`
  ```json
  [
      {
          "id": 1,
          "name": "Alice J. Williams",
          "email": "alice.williams@example.com",
          "course": "Data Science",
          "age": 22
      }
  ]
  ```

---

### Screenshot 7: `DELETE /api/students/{id}` (Delete Student)
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/api/students/1`
- **Response:** `200 OK`
  ```json
  {
      "status": 200,
      "message": "Student deleted successfully with id: 1"
  }
  ```

---

## 🛡️ Exception Handling & Status Codes

The project implements a global exception handling architecture using `@RestControllerAdvice`:
- **`ResourceNotFoundException`**: Thrown when a student with a specified ID does not exist in the database. Returns HTTP `404 Not Found`.
- **`MethodArgumentNotValidException`**: Catches Jakarta Validation errors (e.g. invalid email format, empty name, or age <= 0). Returns HTTP `400 Bad Request` with detailed field error messages.
- **`Exception`**: Fallback handler catching unexpected system exceptions. Returns HTTP `500 Internal Server Error`.

---

## 🌐 Pushing to GitHub (Deliverable Guide)

To submit the GitHub repository link:

1. **Create a new repository on GitHub:**
   - Go to [github.com/new](https://github.com/new).
   - Name it `student-management-system`.
   - Choose **Public**.
   - Do **NOT** initialize with README or .gitignore (we already have them).
   - Click **Create repository**.

2. **Run Git commands in your terminal:**
   Open terminal inside `student-management-system/`:
   ```bash
   git init
   git add .
   git commit -m "Complete Student Management System REST API with Spring Boot, JPA, and MySQL"
   git branch -M main
   git remote add origin https://github.com/<YOUR_GITHUB_USERNAME>/student-management-system.git
   git push -u origin main
   ```

3. **Copy your repository URL** and submit it as requested in Deliverables!
