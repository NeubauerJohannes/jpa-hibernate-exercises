package com.bobocode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TODO: you're job is to implement mapping for JPA entity {@link Movie}
 * - specify id x
 * - configure id as auto-increment column  x
 * - explicitly specify each column name ("id", "name", "director", and "duration" accordingly) x
 * - specify not null constraint for fields {@link Movie#name} and {@link Movie#director}
 */

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "director", nullable = false)
    private String director;
    @Column(name = "duration")
    private Integer durationSeconds;
}
