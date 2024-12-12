package com.rawafed.employeemanagementapi.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String department;

    private double salary;

    public Employee(Employee employeeUpdated) {
        if (employeeUpdated != null) {
            this.id = employeeUpdated.getId();
            this.firstName = employeeUpdated.getFirstName();
            this.lastName = employeeUpdated.getLastName();
            this.email = employeeUpdated.getEmail();
            this.department = employeeUpdated.getDepartment();
            this.salary = employeeUpdated.getSalary();
        }
    }


}

