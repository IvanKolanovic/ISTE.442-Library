package com.ikolanovic.restapi.controllers;

import com.ikolanovic.restapi.models.Book;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;
import com.ikolanovic.restapi.security.isAdmin;
import com.ikolanovic.restapi.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rit-library")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @isAdmin
    @PostMapping("/book")
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createNewBook(book));
    }

    @isAdmin
    @PutMapping("/book/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @isAdmin
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Object> getBook(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/books")
    public ResponseEntity<Object> getAllBooks(CustomPredicate customPredicate) {
        return ResponseEntity.ok(bookService.getAllBooks(customPredicate));
    }
}