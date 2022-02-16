package com.employeeRecord.data.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private Long salary;
    private String email;
    private String  dateOfBirth;
    private MultipartFile image;
}

