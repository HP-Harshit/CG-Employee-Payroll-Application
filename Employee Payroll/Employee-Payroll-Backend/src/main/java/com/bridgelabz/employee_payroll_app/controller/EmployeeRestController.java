package com.bridgelabz.employee_payroll_app.controller;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.exception.EmployeeNotFoundException;
import com.bridgelabz.employee_payroll_app.model.Employee;
import com.bridgelabz.employee_payroll_app.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeeRestController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeRestController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: " + id);
        return employeeService.getEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: " + id);
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        log.info("Fetching employees from department: " + department);
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        log.info("Fetching employees with name: " + name);
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/search/{searchText}")
    public List<Employee> searchEmployees(@PathVariable String searchText) {
        log.info("Searching employees with text: " + searchText);
        return employeeService.searchEmployees(searchText);
    }
}
