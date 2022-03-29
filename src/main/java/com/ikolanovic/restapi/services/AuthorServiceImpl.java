package com.ikolanovic.restapi.services;

import com.ikolanovic.restapi.exceptions.RequestFailedException;
import com.ikolanovic.restapi.exceptions.ResourceNotFoundException;
import com.ikolanovic.restapi.models.Author;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;
import com.ikolanovic.restapi.repos.AuthorRepository;
import com.ikolanovic.restapi.services.filtering.CustomQueryBuilder;
import com.ikolanovic.restapi.services.interfaces.AuthorService;
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
public class AuthorServiceImpl extends BaseService implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CustomQueryBuilder queryBuilder;

    @Override
    @Transactional
    public Author createAuthor(Author author) {

        author.setActive(true);
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(resourceNotFound("Author with id: " + id + " was not found."));
    }

    @Override
    public List<Author> getAllAuthors(CustomPredicate customPredicate) {
        BooleanBuilder builder = queryBuilder.buildAuthorFilterQuery(customPredicate);

        if (Optional.ofNullable(builder.getValue()).isEmpty())
            throw new RequestFailedException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error during query generation.");

        Iterable<Author> iterable = authorRepository.findAll(builder.getValue());
        List<Author> authors = new ArrayList<>();

        iterable.forEach(authors::add);
        return authors;
    }

    @Override
    @Transactional
    public boolean deleteAuthor(Long id) {
        authorRepository.delete(authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id:" + id + " not found.")));
        return true;
    }

    @Override
    @Transactional
    public Author updateAuthor(Long id, Author input) {
        Author updatedAuthor =
                authorRepository
                        .findById(id)
                        .map(
                                author -> {

                                    if (Optional.ofNullable(input.getName()).isPresent())
                                        author.setName(input.getName());

                                    if (Optional.ofNullable(input.getLocation()).isPresent())
                                        author.setLocation(input.getLocation());

                                    if (Optional.ofNullable(input.getBio()).isPresent())
                                        author.setBio(input.getBio());

                                    if (Optional.ofNullable(input.getDob()).isPresent())
                                        author.setDob(input.getDob());

                                    return author;
                                })
                        .orElseThrow(resourceNotFound("Author with id " + id + " could not be found."));

        return authorRepository.saveAndFlush(updatedAuthor);
    }

}
