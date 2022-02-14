package com.ikolanovic.restapi.services.interfaces;

public interface LibraryService {

    String loanBook(Long userId, Long bookId);

    String returnBook(Long userId, Long bookId);

}
