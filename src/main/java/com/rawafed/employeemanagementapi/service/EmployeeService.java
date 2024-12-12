package com.rawafed.employeemanagementapi.service;

import com.rawafed.employeemanagementapi.config.EmailNotificationConfig;
import com.rawafed.employeemanagementapi.model.Employee;
import com.rawafed.employeemanagementapi.exception.EmployeeNotFoundException;
import com.rawafed.employeemanagementapi.exception.InvalidInputException;
import com.rawafed.employeemanagementapi.repository.EmployeeRepository;
import com.rawafed.employeemanagementapi.validation.DepartmentValidationService;
import com.rawafed.employeemanagementapi.validation.EmailValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmailValidationService emailValidationService;
    private final DepartmentValidationService departmentValidationService;
    private final EmailNotificationConfig emailNotificationConfig;

    public EmployeeService(EmployeeRepository employeeRepository, EmailValidationService emailValidationService, DepartmentValidationService departmentValidationService, EmailNotificationConfig emailNotificationConfig) {
        this.employeeRepository = employeeRepository;
        this.emailValidationService = emailValidationService;
        this.departmentValidationService = departmentValidationService;
        this.emailNotificationConfig = emailNotificationConfig;
    }


    @Transactional
    public Employee createEmployee(Employee employee) {
        if (!emailValidationService.isValidEmail(employee.getEmail())) {
            throw new InvalidInputException("Invalid email format");
        }

        if (!departmentValidationService.isValidDepartment(employee.getDepartment())) {
            throw new InvalidInputException("Invalid department");
        }
        emailNotificationConfig.sendEmployeeCreationEmail(employee);
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public Employee updateEmployee(Long id, Employee employee) {
        getEmployeeById(id);
        return employeeRepository.save(new Employee(employee));
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
