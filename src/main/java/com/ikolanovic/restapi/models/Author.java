package com.ikolanovic.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column
    private String location;

    @Basic
    @Column
    private String bio;

    @Basic
    @Column(nullable = false)
    private LocalDate dob;

    @Basic
    @Column(nullable = false)
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.REMOVE)
    private List<Book> books = List.of();

}
