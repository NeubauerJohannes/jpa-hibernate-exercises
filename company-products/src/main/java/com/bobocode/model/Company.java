package com.bobocode.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * todo:
 * - implement no arguments constructor                                                                     x
 * - implement getters and setters for all fields                                                           x
 * - implement equals() and hashCode() based on identifier field                                            x
 * - make setter for field {@link Company#products} private                                                 x
 * - initialize field {@link Company#products} as new {@link ArrayList}                                     x
 * - implement a helper {@link Company#addProduct(Product)} that establishes a relation on both sides       x
 * - implement a helper {@link Company#removeProduct(Product)} that drops a relation on both sides          x?
 * <p>
 * - configure JPA entity                                               x
 * - specify table name: "company"                                      x
 * - configure auto generated identifier                                x
 * - configure mandatory column "name" for field {@link Company#name}   x
 * <p>
 * - configure one to many relationship as mapped on the child side     x
 */
@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "company", fetch =  FetchType.LAZY)
    private List<Product> products = new ArrayList<Product>();

    public Company(){
        this.products = new ArrayList<Product>();
    }


    public void addProduct(Product product) {
        this.products.add(product);
        product.setCompany(this);
    }



    public void removeProduct(Product product) {
        products.remove(product);
        product.setCompany(null);
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

    public List<Product> getProducts() {
        return products;
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
