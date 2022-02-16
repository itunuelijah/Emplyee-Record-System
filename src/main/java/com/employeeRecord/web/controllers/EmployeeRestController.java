package com.employeeRecord.web.controllers;

import com.github.fge.jsonpatch.JsonPatch;
import com.employeeRecord.data.dto.EmployeeDto;
import com.employeeRecord.data.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.employeeRecord.service.employee.EmployeeService;
import com.employeeRecord.web.exceptions.BusinessLogicException;

import java.util.List;

@RestController
@RequestMapping ("api/employees")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
   public ResponseEntity<?> findAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employeeList);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createEmployee(@ModelAttribute EmployeeDto employeeDto){

        try{
            Employee savedEmployee = employeeService.createEmployee(employeeDto);
             return ResponseEntity.ok().body(savedEmployee);
        } catch (BusinessLogicException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody JsonPatch employeePatch){

        try {
            Employee updatedEmployee = employeeService.updateEmployeeDetails(id, employeePatch);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        } catch (BusinessLogicException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}