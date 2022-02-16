package com.employeeRecord.service.employee;


import com.employeeRecord.service.employee.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.employeeRecord.data.dto.EmployeeDto;
import com.employeeRecord.data.models.Employee;
import com.employeeRecord.data.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeeRecord.web.exceptions.BusinessLogicException;
import com.employeeRecord.web.exceptions.EmployeeDoesNotExistException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee findEmployeeById(Long employeeId) throws EmployeeDoesNotExistException{
        if(employeeId == null){
            throw new IllegalArgumentException("ID cannot be null.");
        }
        Optional<Employee> queryResult = employeeRepository.findById(employeeId);
        if(queryResult.isPresent()){
            return queryResult.get();
        }

        throw new EmployeeDoesNotExistException("Employee with ID :"+employeeId +": does not exists");
    }







    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws BusinessLogicException {

        if (employeeDto == null) {
            throw new IllegalArgumentException("Argument cannot be null. ");
        }
        Optional<Employee> query = employeeRepository.findByFirstName(employeeDto.getFirstName());
        if (query.isPresent()) {
            throw new BusinessLogicException("employee with name" + employeeDto.getFirstName() +
                    " already exists");
        }
        log.info("Creating object --> {}", employeeDto);
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setSalary(employeeDto.getSalary());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());

        return employeeRepository.save(employee);
    }


    private Employee saveOrUpdate(Employee employee) throws BusinessLogicException {
        if (employee == null) {
            throw new BusinessLogicException("Employee cannot be null");
        }
        return employeeRepository.save(employee);
    }


    @Override
    public Employee updateEmployeeDetails(Long employeeId, JsonPatch employeePatch) throws BusinessLogicException {
        Optional<Employee> employeeQuery = employeeRepository.findEmployeeById(employeeId);
        if (employeeQuery.isEmpty()) {
            throw new BusinessLogicException("Employee with ID " + employeeId + " does not exists");
        }
        Employee targetEmployee = employeeQuery.get();

        try {
            targetEmployee = applyPatchToEmployee(employeePatch, targetEmployee);
            log.info("Employee after patch --> {} ", targetEmployee);
            return saveOrUpdate(targetEmployee);
        } catch (JsonPatchException | JsonProcessingException | BusinessLogicException je) {
            throw new BusinessLogicException("Update Failed");
        }

    }

    private Employee applyPatchToEmployee(JsonPatch employeePatch, Employee targetEmployee) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = employeePatch.apply(objectMapper.convertValue(targetEmployee, JsonNode.class));
        return objectMapper.treeToValue(patched, Employee.class);
    }





    @Override
    public String deleteEmployee(Employee employee) throws BusinessLogicException {

        employeeRepository.findEmployeeById(employee.getId());
        if (employee == null) {
            throw new BusinessLogicException("Employee cannot be null");
        }

        log.info("Deleting object --> {} ", employee);
          employeeRepository.delete(employee);
          return "Employee deleted successfully";



    }

    @Override
    public String deleteAllEmployees() {
        employeeRepository.deleteAll();
        return "All employees deleted";
    }

}