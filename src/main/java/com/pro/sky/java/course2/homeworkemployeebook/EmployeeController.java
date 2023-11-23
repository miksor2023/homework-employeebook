package com.pro.sky.java.course2.homeworkemployeebook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    };
    @GetMapping
    public String sayWelcome() {
        return "Добро пожаловать в книгу учёта сотрудников";
    }
    @GetMapping("/add")
    public Employee addEmployee (@RequestParam(name = "firstName", required = true) String firstName,
                                 @RequestParam(name = "lastName", required = true) String lastName) {
        Employee employee = new Employee(firstName, lastName);
            employeeService.addEmployee(employee);
        return employee;
    }

    @GetMapping("/find")
    public Employee findEmployee (@RequestParam(name = "firstName", required = true) String firstName,
                                  @RequestParam(name = "lastName", required = true) String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.findEmployee(employee);
        return employee;
    }
    @GetMapping("/remove")
    public Employee removeEmployee (@RequestParam(name = "firstName", required = true) String firstName,
                                    @RequestParam(name = "lastName", required = true) String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.removeEmployee(employee);
        return employee;
    }
    @GetMapping("/print")
    public List<Employee> printEmployeeList() {
        return employeeService.returnEmployeeList();
    }
    @GetMapping("/load")
    public List<Employee> loadEmployeeList() {
        employeeService.loadEmployeeList();
        return employeeService.returnEmployeeList();
    }
}
