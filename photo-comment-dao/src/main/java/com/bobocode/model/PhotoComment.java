package com.bobocode.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * todo:
 * - implement no argument constructor
 * - implement getters and setters
 * - implement equals and hashCode based on identifier field
 * <p>
 * - configure JPA entity                   x
 * - specify table name: "photo_comment"    x
 * - configure auto generated identifier    x
 * - configure not nullable column: text    x
 * <p>
 * - map relation between Photo and PhotoComment using foreign_key column: "photo_id"   x?
 * - configure relation as mandatory (not optional) ?
 */
@Entity
@Table(name = "photo_comment")
public class PhotoComment {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String text;
    private LocalDateTime createdOn;
    @ManyToOne(targetEntity = Photo.class, optional = false)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public PhotoComment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoComment that = (PhotoComment) o;
        return Objects.equals(id, that.id);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
            photo.addComment(this);

    }
}
