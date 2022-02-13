package service;

import com.github.fge.jsonpatch.JsonPatch;
import data.dto.EmployeeDto;
import data.models.Employee;
import web.exceptions.BusinessLogicException;
import web.exceptions.EmployeeDoesNotExistException;


import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee findEmployeeById(EmployeeDto employeeDto) throws EmployeeDoesNotExistException;
    Employee creatEmployee(EmployeeDto employeeDto) throws BusinessLogicException;
    Employee UpdateEmployeeDetails(EmployeeDto employeeDto) throws BusinessLogicException;
    Employee delete(EmployeeDto employeeDto) throws BusinessLogicException;
}

