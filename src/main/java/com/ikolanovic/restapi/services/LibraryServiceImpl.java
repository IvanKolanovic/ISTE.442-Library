package com.ikolanovic.restapi.services;

import com.ikolanovic.restapi.models.Book;
import com.ikolanovic.restapi.models.User;
import com.ikolanovic.restapi.repos.BookRepository;
import com.ikolanovic.restapi.repos.UserRepository;
import com.ikolanovic.restapi.services.interfaces.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl extends BaseService implements LibraryService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public String loanBook(Long userId, Long bookId) {

        if (!getCurrentUserId().equals(userId))
            throw new AuthorizationServiceException("User ID does not match current user.");

        Book book = bookRepository.findByIdAndAvailableTrue(bookId).orElseThrow(resourceNotFound("Book with id: " + bookId + " is not available or does not exist!"));
        User user = getCurrentUser();

        book.setBorrowedBy(user);
        book.setAvailable(false);
        bookRepository.saveAndFlush(book);

        return "Book \"" + book.getTitle() + "\" has been loaned to " + user.getFullName() + ".";
    }

    @Override
    public String returnBook(Long userId, Long bookId) {

        if (!getCurrentUserId().equals(userId))
            throw new AuthorizationServiceException("User ID does not match current user.");

        Book book = bookRepository.findByIdAndAvailableFalse(bookId).orElseThrow(resourceNotFound("Book with id: " + bookId + " does not exist or is not loaned by the current user!"));
        User user = book.getBorrowedBy();

        book.setBorrowedBy(null);
        book.setAvailable(true);

        bookRepository.saveAndFlush(book);

        return user.getFullName() + " has returned" + " \"" + book.getTitle() + "\".";
    }
}
