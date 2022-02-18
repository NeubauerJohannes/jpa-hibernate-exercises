package com.bobocode.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * todo:
 * - implement no argument constructor  x
 * - implement getters and setters      x
 * - make a setter for field {@link Photo#comments} {@code private}    x
 * - implement equals() and hashCode() based on identifier field       x
 * <p>
 * - configure JPA entity     x
 * - specify table name: "photo"     x
 * - configure auto generated identifier      x
 * - configure not nullable and unique column: url        x
 * <p>
 * - initialize field comments              x?
 * - map relation between Photo and PhotoComment on the child side          x
 * - implement helper methods {@link Photo#addComment(PhotoComment)} and {@link Photo#removeComment(PhotoComment)}  x
 * - enable cascade type {@link javax.persistence.CascadeType#ALL} for field {@link Photo#comments} x
 * - enable orphan removal  x
 */
@Entity
@Table(name = "photo")
@NamedQuery(name = "PhotoFindByDescription",query = "select p from Photo p where p.description like :description")
public class Photo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String url;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoComment> comments = new ArrayList<PhotoComment>();


    public Photo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoComment> getComments() {
        return comments;
    }

    private void setComments(List<PhotoComment> comments) {
        this.comments = comments;
    }

    public void addComment(PhotoComment comment) {
        this.comments.add(comment);
        if(!comment.getPhoto().equals(this)) {
            comment.setPhoto(this);
        }
    }

    public void removeComment(PhotoComment comment) {
        this.comments.remove(comment);
        comment.setPhoto(null);
    }

}
