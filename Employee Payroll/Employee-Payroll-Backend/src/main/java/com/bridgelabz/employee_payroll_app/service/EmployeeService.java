package com.bridgelabz.employee_payroll_app.service;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.exception.EmployeeNotFoundException;
import com.bridgelabz.employee_payroll_app.model.Employee;
import com.bridgelabz.employee_payroll_app.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employee.setGender(employeeDTO.getGender());
            employee.setDay(employeeDTO.getDay());
            employee.setMonth(employeeDTO.getMonth());
            employee.setYear(employeeDTO.getYear());
            employee.setStartDate(employeeDTO.getStartDate());
            employee.setNote(employeeDTO.getNote());
            employee.setProfilePic(employeeDTO.getProfilePic());
            employee.setDepartment(employeeDTO.getDepartment());
            log.info("Created employee with name: " + employee.getName());
            return employeeRepository.save(employee);
        } catch (Exception e) {
            log.error("Error creating employee: ", e);
            throw new RuntimeException("Error creating employee", e);
        }
    }


    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: " + id);
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setDay(employeeDTO.getDay());
        employee.setMonth(employeeDTO.getMonth());
        employee.setYear(employeeDTO.getYear());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());
        log.info("Updated employee with ID: " + id);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employeeRepository.delete(employee);
        log.info("Deleted employee with ID: " + id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        log.info("Fetching employees from department: " + department);
        return employeeRepository.findEmployeesByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        log.info("Fetching employees with name: " + name);
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Employee> searchEmployees(String searchText) {
        log.info("Searching employees with text: " + searchText);
        List<Employee> employees = employeeRepository.findByNameContainingIgnoreCase(searchText);
        employees.addAll(employeeRepository.findEmployeesByDepartment(searchText));
        return employees;
    }
}
