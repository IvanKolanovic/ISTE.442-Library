package com.ikolanovic.restapi.repos;

import com.ikolanovic.restapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

    Optional<Book> findByTitle(String title);

    Optional<Book> findByIdAndAvailableTrue(Long id);

    Optional<Book> findByIdAndAvailableFalse(Long id);

}
