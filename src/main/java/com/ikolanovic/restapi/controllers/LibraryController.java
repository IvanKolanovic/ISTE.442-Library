package com.ikolanovic.restapi.controllers;

import com.ikolanovic.restapi.services.interfaces.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rit-library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PutMapping("/library/user/{userId}/loan-book/{bookId}")
    public ResponseEntity<String> loanBook(@PathVariable(value = "userId") Long userId, @PathVariable(value = "bookId") Long bookId) {
        return ResponseEntity.ok(libraryService.loanBook(userId, bookId));
    }

    @PutMapping("/library/user/{userId}/return-book/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable(value = "userId") Long userId,@PathVariable(value = "bookId") Long bookId) {
        return ResponseEntity.ok(libraryService.returnBook(userId,bookId));
    }
}
