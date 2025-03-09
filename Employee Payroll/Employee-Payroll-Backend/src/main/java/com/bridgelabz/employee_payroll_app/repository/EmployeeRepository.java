package com.bridgelabz.employee_payroll_app.repository;

import com.bridgelabz.employee_payroll_app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByDepartment(String department);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
