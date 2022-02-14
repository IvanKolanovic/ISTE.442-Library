package com.ikolanovic.restapi.models.predicates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomPredicate {

    private String author;

    private String title;

    private String genre;

    private String publishDate;

    private String dob;
    
    private String location;
}
