package com.rawafed.employeemanagementapi.repository;

import com.rawafed.employeemanagementapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
