package com.employeeRecord.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false)
    private String lastName;
    private String gender;
    private String  dateOfBirth;
    private Long salary;
    private String email;
    private String phoneNumber;
    private String address;
    @CreationTimestamp
    private LocalDate dateEmployed;
    private String imageUrl;
}
