package com.bobocode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * todo:
 * - implement no arguments constructor                             x
 * - implement getters and setters for all fields                   x
 * - implement equals() and hashCode() based on identifier field    x
 * <p>
 * - configure JPA entity                                           x
 * - specify table name: "product"                                  x
 * - configure auto generated identifier                            x
 * - configure mandatory column "name" for field {@link Product#name}x
 * <p>
 * - configure lazy many-to-one relation between {@link Product} and {@link Company}x
 * - configure foreign key column "company_id" references company table             x
 */
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;



    public Product() {

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}