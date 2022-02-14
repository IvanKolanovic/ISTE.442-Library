package com.ikolanovic.restapi.controllers;

import com.ikolanovic.restapi.models.Author;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;
import com.ikolanovic.restapi.security.isAdmin;
import com.ikolanovic.restapi.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rit-library")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @isAdmin
    @PostMapping("/author")
    public ResponseEntity<Object> createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @isAdmin
    @PutMapping("/author/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") Long id, @RequestBody Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }

    @isAdmin
    @DeleteMapping("/author/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @GetMapping("/authors")
    public ResponseEntity<Object> getAllAuthors(CustomPredicate customPredicate) {
        return ResponseEntity.ok(authorService.getAllAuthors(customPredicate));
    }
}
