package com.rawafed.employeemanagementapi;

import com.rawafed.employeemanagementapi.model.Employee;
import com.rawafed.employeemanagementapi.validation.DepartmentValidationService;
import com.rawafed.employeemanagementapi.validation.EmailValidationService;
import com.rawafed.employeemanagementapi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Mock
    private EmailValidationService emailValidationService;

    @Mock
    private DepartmentValidationService departmentValidationService;


    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmail("test@example.com");
        employee.setDepartment("HR");
        when(emailValidationService.isValidEmail(employee.getEmail())).thenReturn(true);
        when(departmentValidationService.isValidDepartment(employee.getDepartment())).thenReturn(true);
        Employee createdEmployee = employeeService.createEmployee(employee);
        assertNotNull(createdEmployee);
    }
}

