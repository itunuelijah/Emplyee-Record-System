package service;


import data.dto.EmployeeDto;
import data.models.Employee;
import data.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.exceptions.BusinessLogicException;
import web.exceptions.EmployeeDoesNotExistException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImply implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws EmployeeDoesNotExistException {

       if(employeeId == null ){
           throw new IllegalArgumentException("ID cannot be null. ");
       }

       Optional <Employee> queryResult = employeeRepository.findById(employeeId);
       if(queryResult.isPresent()){
           return queryResult.get();
       }
        throw new EmployeeDoesNotExistException("Product with ID :"+employeeId +": does not exists");
    }

    @Override
    public Employee creatEmployee(EmployeeDto employeeDto) throws BusinessLogicException {

        if (employeeDto == null){
           throw new IllegalArgumentException("Argument cannot be null. ");
        }
      Optional <Employee> query = employeeRepository.findByFirstName(employeeDto.getFirstName())
      if(query.isPresent()){
          throw new BusinessLogicException("employee with name" + employeeDto.getFirstName() +
                  " already exists");
      }

    }

    @Override
    public Employee UpdateEmployeeDetails(EmployeeDto employeeDto) throws BusinessLogicException {
        return null;
    }

    @Override
    public Employee delete(EmployeeDto employeeDto) throws BusinessLogicException {
        return null;
    }
}