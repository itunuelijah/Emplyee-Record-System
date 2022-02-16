package com.employeeRecord.data.repository;

import com.employeeRecord.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFirstName (String firstName);

    Optional<Employee> findEmployeeById(Long employeeId);

}
