package com.bobocode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * todo:
 * - implement no argument constructor                          x
 * - implement getters and setters                              x
 * - implement equals and hashCode based on identifier field    x
 *
 * - configure JPA entity                                       x
 * - specify table name: "employee"                             x
 * - configure auto generated identifier                        x
 * - configure not nullable columns: email, firstName, lastName x
 *
 * - map unidirectional relation between {@link Employee} and {@link EmployeeProfile} on the child side x?
 */

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue


    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String fistName;
    @Column(nullable = false)
    private String lastName;

    public Employee(){

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
