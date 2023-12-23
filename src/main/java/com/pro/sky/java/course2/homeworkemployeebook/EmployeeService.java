package com.pro.sky.java.course2.homeworkemployeebook;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employeeList;

    public EmployeeService(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private static int maxEmployeeQty = 5;

//метод производит проверку входных данных и возвращает объект Employee c отформатированным именем
    public Employee createEmployeeWithValidName(String firstName, String lastName) throws InvalidEmployeeNameException{
        if (StringUtils.isAlpha(firstName)&&StringUtils.isAlpha(lastName)) {
            firstName = StringUtils.capitalize(StringUtils.lowerCase(firstName));
            lastName = StringUtils.capitalize(StringUtils.lowerCase(lastName));
            return new Employee(firstName, lastName);
        } else {
            throw new InvalidEmployeeNameException("Неправильный формат имени/фамилии");
        }
    }

    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException{
        Employee employee = createEmployeeWithValidName(firstName, lastName);
        if (employeeList.size() < maxEmployeeQty && employeeList.contains(employee) == false) {
            employeeList.add(employee);
            return employee;
        } else if (employeeList.contains(employee) == true){
            throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже существует");
        } else {
            throw new EmployeeStorageIsFullException("Хранилище заполнено");
        }
    }
    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException{
        Employee employee = createEmployeeWithValidName(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник с таким именем не найден");
        }
    }

    public Employee findEmployee (String firstName, String lastName) throws EmployeeNotFoundException{
        Employee employee = createEmployeeWithValidName(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник с таким именем не найден");
        }
    }
    public List<Employee> returnEmployeeList() {
        return employeeList;
    }

    //тестовый метод для заполнения списка записями
    public List<Employee> loadEmployeeList() {
        employeeList.add(new Employee("Ivan", "Ivanov"));
        employeeList.add(new Employee("Ivan", "Sidorov"));
        employeeList.add(new Employee("Ivan", "Petrov"));
        employeeList.add(new Employee("Ivan", "Vetrov"));
        employeeList.add(new Employee("Ivan", "Shpetrov"));
        return employeeList;
    }
}
