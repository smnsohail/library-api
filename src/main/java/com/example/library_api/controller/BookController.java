package com.example.library_api.controller;

import com.example.library_api.model.Book;
import com.example.library_api.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private Map<String, Object> buildResponse(int code, String message, Object data) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("statusCode", code);
        response.put("message", message);
        if (data != null) response.put("data", data);
        return response;
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody Book book){
        try {
            Book newBook = bookService.addBook(book);
            return ResponseEntity.status(201).body(buildResponse(201, "Book added successfully", newBook));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(buildResponse(400, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(buildResponse(500, "Internal error: " + e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllBooks(){
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(buildResponse(200, "Books fetched successfully", books));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(buildResponse(500, "Internal error: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Integer id){
        try {
            Book book = bookService.getBookById(id);
            if (book == null) {
                return ResponseEntity.status(404).body(buildResponse(404, "Book not found with ID: " + id, null));
            }
            return ResponseEntity.ok(buildResponse(200, "Book fetched successfully", book));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(buildResponse(500, "Internal error: " + e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook){
        try {
            Book updated = bookService.updateBook(id, updatedBook);
            return ResponseEntity.ok(buildResponse(200, "Book updated successfully", updated));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(buildResponse(404, e.getMessage(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(buildResponse(400, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(buildResponse(500, "Internal error: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Integer id){
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok(buildResponse(200, "Book deleted successfully", null));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(buildResponse(404, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(buildResponse(500, "Internal error: " + e.getMessage(), null));
        }
    }
}
