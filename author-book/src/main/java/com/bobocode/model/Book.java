package com.bobocode.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * todo:
 * - implement no arguments constructor                                                                     x
 * - implement getters and setters for all fields                                                           x
 * - implement equals() and hashCode() based on {@link Book#isbn}                                           x
 * - make setter for field {@link Book#authors} private                                                     x
 * - initialize field {@link Book#authors} as new {@link HashSet}                                           x
 * <p>
 * - configure JPA entity                                                                                   x
 * - specify table name: "book"                                                                             x
 * - configure auto generated identifier                                                                    x
 * - configure mandatory column "name" for field {@link Book#name}                                          x
 * - configure mandatory unique column "isbn" for field {@link Book#isbn}, it is a natural key candidate    x
 * <p>
 * - configure many-to-many relation as mapped on the {@link Author} side                                   x
 */


@Entity(name = "book")

public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    @NaturalId
    private String isbn;
    @ManyToMany(mappedBy = "books")

    private Set<Author> authors;

    public Book() {
        this.authors = new HashSet<Author>();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public Set<Author> getAuthors() {
        return authors;
    }

    private void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
