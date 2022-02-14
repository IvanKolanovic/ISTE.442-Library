package com.ikolanovic.restapi.services.interfaces;


import com.ikolanovic.restapi.models.Book;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;

import java.util.List;

public interface BookService {

    Book getBookByTitle(String name);

    Book getBookById(Long id);

    List<Book> getAllBooks(CustomPredicate customPredicate);

    Book createNewBook(Book book);

    boolean deleteBook(Long id);

    Book updateBook(Long id, Book input);

}
