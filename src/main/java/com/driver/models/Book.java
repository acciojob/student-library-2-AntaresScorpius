package com.driver.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("booksWritten")
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    @JsonIgnore
    private Card card;


    @Column(columnDefinition = "TINYINT(1)")
    private boolean available = true;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    @JsonIgnore
    private List<Transaction> transactions;

    public Book(String name, Genre genre, boolean available,  Author author) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.available = available;
    }
    public Book(String name, Genre genre,  Author author) {
        this.name = name;
        this.genre = genre;
        this.author = author;
    }

    public Book() {
    }
}

