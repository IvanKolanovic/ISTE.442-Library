package com.ikolanovic.restapi.services;

import com.ikolanovic.restapi.exceptions.RequestFailedException;
import com.ikolanovic.restapi.exceptions.ResourceNotFoundException;
import com.ikolanovic.restapi.models.Book;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;
import com.ikolanovic.restapi.repos.AuthorRepository;
import com.ikolanovic.restapi.repos.BookRepository;
import com.ikolanovic.restapi.services.filtering.CustomQueryBuilder;
import com.ikolanovic.restapi.services.interfaces.BookService;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl extends BaseService implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CustomQueryBuilder queryBuilder;

    @Override
    public Book getBookByTitle(String name) {
        return bookRepository.findByTitle(name).orElseThrow(resourceNotFound("Book titled: " + name + " was not found."));
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(resourceNotFound("Book with id: " + id + " was not found."));
    }

    @Override
    public List<Book> getAllBooks(CustomPredicate customPredicate) {
        BooleanBuilder builder = queryBuilder.buildBookFilterQuery(customPredicate);

        if (Optional.ofNullable(builder.getValue()).isEmpty())
            throw new RequestFailedException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during query generation.");

        Iterable<Book> iterable = bookRepository.findAll(builder.getValue());
        List<Book> books = new ArrayList<>();

        iterable.forEach(books::add);
        return books;
    }

    @Override
    @Transactional
    public Book createNewBook(Book book) {
        book.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElseThrow(resourceNotFound("Author with id:" + book.getAuthor().getId() + " not found.")));
        book.setAvailable(true);
        return bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional
    public boolean deleteBook(Long id) {
        bookRepository.delete(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id:" + id + " not found.")));
        return true;
    }

    @Override
    @Transactional
    public Book updateBook(Long id, Book input) {
        Book updatedBook =
                bookRepository
                        .findById(id)
                        .map(
                                book -> {

                                    if (Optional.ofNullable(input.getTitle()).isPresent())
                                        book.setTitle(input.getTitle());

                                    if (Optional.ofNullable(input.getGenre()).isPresent())
                                        book.setGenre(input.getGenre());

                                    if (Optional.ofNullable(input.getDescription()).isPresent())
                                        book.setDescription(input.getDescription());

                                    if (Optional.ofNullable(input.getPublishDate()).isPresent())
                                        book.setPublishDate(input.getPublishDate());

                                    if (Optional.ofNullable(input.getAuthor()).isPresent()) {
                                        book.setAuthor(authorRepository.findById(input.getAuthor().getId()).orElseThrow(resourceNotFound("Author with id:" + input.getAuthor().getId() + " not found.")));
                                    }

                                    return book;
                                })
                        .orElseThrow(resourceNotFound("Book with id " + id + " could not be found."));

        return bookRepository.saveAndFlush(updatedBook);
    }
}