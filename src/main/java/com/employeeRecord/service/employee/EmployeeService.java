package com.employeeRecord.service.employee;

import com.github.fge.jsonpatch.JsonPatch;
import com.employeeRecord.data.dto.EmployeeDto;
import com.employeeRecord.data.models.Employee;
import com.employeeRecord.web.exceptions.BusinessLogicException;
import com.employeeRecord.web.exceptions.EmployeeDoesNotExistException;


import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee findEmployeeById(Long employeeId) throws EmployeeDoesNotExistException;
    Employee createEmployee(EmployeeDto employeeDto) throws BusinessLogicException;
    Employee updateEmployeeDetails(Long productId, JsonPatch productPatch) throws BusinessLogicException;
    String deleteEmployee(Employee employee) throws BusinessLogicException;
    String deleteAllEmployees();


}

