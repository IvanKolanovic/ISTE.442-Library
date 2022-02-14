package com.ikolanovic.restapi.services.filtering;

import com.ikolanovic.restapi.models.QAuthor;
import com.ikolanovic.restapi.models.QBook;
import com.ikolanovic.restapi.models.predicates.CustomPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomQueryBuilder {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @PersistenceContext
    private final EntityManager em;

    public BooleanBuilder buildBookFilterQuery(CustomPredicate predicate) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (Optional.ofNullable(predicate.getAuthor()).isPresent()) {
            String[] arr = predicate.getAuthor().split("");

            booleanBuilder.and(QBook.book.author.firstName.containsIgnoreCase(arr[0]));
            booleanBuilder.and(QBook.book.author.firstName.containsIgnoreCase(arr[1]));
        }

        if (Optional.ofNullable(predicate.getTitle()).isPresent()) {
            booleanBuilder.and(QBook.book.title.containsIgnoreCase(predicate.getTitle()));
        }

        if (Optional.ofNullable(predicate.getGenre()).isPresent()) {
            booleanBuilder.and(QBook.book.genre.containsIgnoreCase(predicate.getGenre()));
        }

        if (Optional.ofNullable(predicate.getPublishDate()).isPresent()) {
            LocalDate publishDate = LocalDate.parse(predicate.getPublishDate(), formatter);
            booleanBuilder.and(QBook.book.publishDate.eq(publishDate));
        }

        booleanBuilder.and(QBook.book.available.isTrue());

        return booleanBuilder;
    }

    public BooleanBuilder buildAuthorFilterQuery(CustomPredicate predicate) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (Optional.ofNullable(predicate.getAuthor()).isPresent()) {
            String[] arr = predicate.getAuthor().split("");

            booleanBuilder.and(QAuthor.author.firstName.containsIgnoreCase(arr[0]));
            booleanBuilder.and(QAuthor.author.firstName.containsIgnoreCase(arr[1]));
        }

        if (Optional.ofNullable(predicate.getLocation()).isPresent()) {
            booleanBuilder.and(QAuthor.author.location.containsIgnoreCase(predicate.getLocation()));
        }

        if (Optional.ofNullable(predicate.getDob()).isPresent()) {
            LocalDate date = LocalDate.parse(predicate.getDob(), formatter);
            booleanBuilder.and(QAuthor.author.dob.eq(date));
        }

        booleanBuilder.and(QAuthor.author.active.isTrue());

        return booleanBuilder;
    }
}
