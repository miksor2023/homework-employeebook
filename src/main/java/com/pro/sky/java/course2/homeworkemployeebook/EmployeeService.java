package com.pro.sky.java.course2.homeworkemployeebook;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public void addEmployee(Employee employee) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException{
        if (employeeList.size() < maxEmployeeQty && employeeList.contains(employee) == false) {
            employeeList.add(employee);
        } else if (employeeList.contains(employee) == true){
            throw new EmployeeAlreadyAddedException();
        } else {
            throw new EmployeeStorageIsFullException();
        }
    }
    public void removeEmployee(Employee employee) {
        if (employeeList.contains(employee) == true) {
            employeeList.remove(employee);
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public void findEmployee (Employee employee) {
        if (employeeList.contains(employee) == true) {
        } else {
            throw new EmployeeNotFoundException();
        }
    }
    public List<Employee> returnEmployeeList() {
        return employeeList;
    }

    //тестовый метод для заполнения списка записями
    public void loadEmployeeList() {
        employeeList.add(new Employee("Ivan", "Ivanov"));
        employeeList.add(new Employee("Ivan", "Sidorov"));
        employeeList.add(new Employee("Ivan", "Petrov"));
        employeeList.add(new Employee("Ivan", "Vetrov"));
        employeeList.add(new Employee("Ivan", "Shpetrov"));
    }
}
