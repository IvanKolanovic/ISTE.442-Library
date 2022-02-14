package com.ikolanovic.restapi.services.interfaces;


import com.ikolanovic.restapi.models.Author;
import com.ikolanovic.restapi.models.User;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;

import java.util.List;


public interface AuthorService {

    Author createAuthor(Author author);

    Author getAuthor(Long id);

    List<Author> getAllAuthors(CustomPredicate customPredicate);

    boolean deleteAuthor(Long userId);

    Author updateAuthor(Long id, Author input);
}
