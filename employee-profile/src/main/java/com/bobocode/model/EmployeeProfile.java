package com.bobocode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * todo:
 * - implement not argument constructor                                     x
 * - implement getters and setters                                          x
 * - implement equals and hashCode based on identifier field                x
 *
 * - configure JPA entity                                                   x
 * - specify table name: "employee_profile"                                 x
 * - configure not nullable columns: position, department                   x
 *
 * - map relation between {@link Employee} and {@link EmployeeProfile} using foreign_key column: "employee_id"  x
 * - configure a derived identifier. E.g. map "employee_id" column should be also a primary key (id) for this entity  x
 */
@Entity
@Table(name = "employee_profile")
public class EmployeeProfile {
    @Id
    private Long id;
    @OneToOne(targetEntity = Employee.class)
    @MapsId()
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String department;


    public EmployeeProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProfile that = (EmployeeProfile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
