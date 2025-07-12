package com.example.library_api.service;

import com.example.library_api.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private final Map<Integer, Book> bookStore = new HashMap<>();
    private int idCounter = 1;

    public Book addBook(Book book) {
        book.setId(idCounter++);
        if (book.getAvailable() == null) {
            book.setAvailable(true);
        }

        bookStore.put(book.getId(), book);
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookStore.values());
    }

    public Book getBookById(Integer id) {
        return bookStore.get(id);
    }

    public boolean deleteBook(Integer id) {
        if (!bookStore.containsKey(id)) {
            throw new NoSuchElementException("Book not found with ID: " + id);
        }
        bookStore.remove(id);
        return true;
    }

    public Book updateBook(Integer id, Book updatedBook) {
        Book existingBook = bookStore.get(id);
        if (existingBook == null) {
            throw new NoSuchElementException("Book not found with ID: " + id);
        }

        if (updatedBook.getTitle() != null && !updatedBook.getTitle().trim().isEmpty()) {
            existingBook.setTitle(updatedBook.getTitle());
        }

        if (updatedBook.getAuthor() != null && !updatedBook.getAuthor().trim().isEmpty()) {
            existingBook.setAuthor(updatedBook.getAuthor());
        }

        if (updatedBook.getIsbn() != null) {
            existingBook.setIsbn(updatedBook.getIsbn());
        }

        if (updatedBook.getAvailable() != null) {
            existingBook.setAvailable(updatedBook.getAvailable());
        }

        return existingBook;
    }
}
