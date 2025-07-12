# 📘 Library Book Catalog API – Java Spring Boot

A simple RESTful API to manage a library’s book catalog.  
This project demonstrates Java, OOP principles, and basic Spring Boot backend design.

---

## ✅ Features

- Add a new book
- Get all books
- Get book details by ID
- Update a book (including title, author, isbn, availability)
- Delete a book
- Input validation (title must not be empty)
- Default availability is set to `true` when adding a new book

---

## 🔧 Tech Stack

- Java 8+
- Spring Boot
- In-memory storage using `Map<Integer, Book>`

---

## 📁 Folder Structure
```
src
└── main
    └── java
        └── com.example.library_api
            ├── controller
            │   └── BookController.java
            ├── model
            │   └── Book.java
            └── service
                └── BookService.java
```

## ▶️ How to Run

### Navigate to project folder:
```bash
  cd library-api
```

### Run the application:
bash
```
./mvnw spring-boot:run
```
#### Or run the project using an IDE like IntelliJ or Eclipse.

## The API will be available at:
```bash
  http://localhost:8080/api/books
```
## 📮 Sample API Requests
### ➕ Add a New Book 
```POST
http://localhost:8080/api/books/api/books
```

```json
{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "isbn": "9780061122415"
}
```
### 📚 Get All Books
```GET
http://localhost:8080/api/books/api/books
```
### 🔍 Get Book by ID
 
```GET
http://localhost:8080/api/books/api/books/1
```
### ✏️ Update Book

```PUT
 http://localhost:8080/api/books/api/books/1
```

```json
{
  "title": "The Alchemist (Updated)",
  "available": false
}
```
### ❌ Delete Book
```DELETE 
http://localhost:8080/api/books/api/books/1
```

### ✅ Sample Success Response
```json
{
  "statusCode": 200,
  "message": "Book fetched successfully",
  "data": {
    "id": 1,
    "title": "The Alchemist",
    "author": "Paulo Coelho",
    "isbn": "9780061122415",
    "available": true
  }
}
```
### ❌ Sample Error Response

```json
{
  "statusCode": 404,
  "message": "Book not found with ID: 99"
}
```
## 📦 Other Information
### ✅ Postman Collection included: 
- Copy & paste this in Postman inport go through this link
```
https://docs.google.com/document/d/1gbg3-NYCiC7yjbaIds_hguT30zPqQITBkfiTkywTxss/edit?usp=sharing
```

### ❌ Database not used (in-memory only)

## 🏁 Final Notes
- Java 8+ required

- Run on port 8080

- No database, fully in-memory

- Returns structured JSON responses

- Built with Spring Boot
