package com.ikolanovic.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = false)
    private String title;

    @Basic
    @Column(nullable = false)
    private String genre;

    @Basic
    @Column
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrowed_by", referencedColumnName = "id")
    private User borrowedBy;

    @Basic
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @Basic
    @Column(nullable = false)
    private boolean available;
}