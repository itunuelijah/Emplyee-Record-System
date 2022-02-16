package com.employeeRecord.repository;

import com.employeeRecord.data.models.Employee;
import com.employeeRecord.data.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})

class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Save a new employee to the database")
    void saveEmployeeToDataBaseTest(){
//        create a new employee

        Employee employee = new Employee();
        employee.setFirstName("Ade");
        employee.setLastName("Olu");
        employee.setEmail("adeokin@gmail.com");
        employee.setSalary(500_00L);

        assertThat(employee.getId()).isNull();
//        save product to database
        employeeRepository.save(employee);
        log.info("Employee saved :: {}", employee);
        assertThat(employee.getId()).isNotNull();

        assertThat(employee.getFirstName()).isEqualTo("Ade");
        assertThat(employee.getLastName()).isEqualTo("Olu");
        assertThat(employee.getEmail()).isEqualTo("adeokin@gmail.com");
        assertThat(employee.getSalary()).isEqualTo(500_00L);

    }


    @Test
    void findByFirstName() {
    }

    @Test
    void findEmployeeById() {
    }
}