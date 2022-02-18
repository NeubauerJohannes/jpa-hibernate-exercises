package com.bobocode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * todo:
 * - implement no arguments constructor                                                                         x
 * - implement getters and setters for all fields                                                               x
 * - implement equals() based on identifier field                                                               x
 * - implement hashCode() that return constant value 31                                                         x
 * - make setter for field {@link Author#books} private                                                         x
 * - initialize field {@link Author#books} as new {@link HashSet}                                               x
 * - implement a helper {@link Author#addBook(Book)} that establishes a relation on both sides                  x
 * - implement a helper {@link Author#removeBook(Book)} that drops a relation on both sides                     x
 * <p>
 * - configure JPA entity                                                                                       x
 * - specify table name: "author"                                                                               x
 * - configure auto generated identifier                                                                        x
 * - configure mandatory column "first_name" for field {@link Author#firstName}                                 x
 * - configure mandatory column "last_name" for field {@link Author#lastName}                                   x
 * <p>
 * - configure many-to-many relation between {@link Author} and {@link Book}                                    x
 * - configure cascade operations for this relations {@link CascadeType#PERSIST} and {@link CascadeType#MERGE}  x
 * - configure link (join) table "author_book"                                                                  x
 * - configure foreign key column "book_id" references book table                                               x?
 * - configure foreign key column "author_id" references author table                                           x?
 */


@Entity(name = "author")

public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))

    private Set<Book> books;


    public Author() {
        this.books = new HashSet<Book>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    private void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public void addBook(Book book) {

        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {

        books.remove(book);
        book.getAuthors().remove(this);
    }


 /*
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerFactory");
            EntityManager em = emf.createEntityManager();
            em.merge(book);
            */

}
